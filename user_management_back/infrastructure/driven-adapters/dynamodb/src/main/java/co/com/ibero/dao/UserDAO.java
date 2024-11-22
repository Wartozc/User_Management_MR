package co.com.ibero.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@AllArgsConstructor
@DynamoDbBean
@Setter
@Getter
@NoArgsConstructor
public class UserDAO {
    private String superUser;
    private String userId;
    private String userName;
    private String userLastName;
    private String userDocumentType;
    private String userDocumentNumber;
    private String userBirthDate;
    private Integer userAge;
    private String userEmail;
    private String userPassword;

    @DynamoDbPartitionKey
    public String getSuperUser() {
        return superUser;
    }

    @DynamoDbSortKey
    public String getUserId() {
        return userId;
    }


}
