package co.com.ibero;

import co.com.ibero.dao.UserDAO;
import co.com.ibero.user.User;
import co.com.ibero.user.gateways.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

@Component
public class DynamoDbAdapter implements UserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;
    private final DynamoDbAsyncTable<UserDAO> dynamoDbAsyncTable;

    public DynamoDbAdapter(DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient,
                           @Value("${app.adapters.dynamodb.table-name}") String tableName) {
        this.modelMapper = new ModelMapper();
        this.dynamoDbEnhancedAsyncClient = dynamoDbEnhancedAsyncClient;
        this.dynamoDbAsyncTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(UserDAO.class));
    }


    @Override
    public Mono<User> createUser(User user) {
        return Mono.fromFuture(dynamoDbAsyncTable.putItem(getUserDao(user)))
                .doOnSuccess(isOk -> logger.info("User created"))
                .thenReturn(user);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return Mono.fromFuture(dynamoDbAsyncTable.putItem(getUserDao(user)))
                .doOnSuccess(isOk -> logger.info("User Updated"))
                .thenReturn(user);
    }

    @Override
    public Flux<User> listUser(String userAdmin) {
        var pagePublisher = dynamoDbAsyncTable.query(getQuery(userAdmin));
        return Flux.from(pagePublisher.items().map(userDAO -> modelMapper.map(userDAO, User.class)))
                .doOnComplete(() -> logger.info("Users Listed"));
    }

    @Override
    public Mono<Boolean> deleteUser(String userId) {
        return Mono.fromFuture(dynamoDbAsyncTable.deleteItem(Key.builder()
                        .partitionValue("wartozc@gmail.com").sortValue(userId).build()))
                .doOnSuccess(isOk -> logger.info("User deleted"))
                .thenReturn(Boolean.TRUE);
    }

    private UserDAO getUserDao(User user) {
        var userDao = modelMapper.map(user, UserDAO.class);
        userDao.setSuperUser("wartozc@gmail.com");
        return userDao;
    }

    private QueryEnhancedRequest getQuery(String partitionKey) {
        return QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.sortGreaterThan(Key.builder()
                        .partitionValue(partitionKey).sortValue("\u0000").build()))
                .build();
    }
}
