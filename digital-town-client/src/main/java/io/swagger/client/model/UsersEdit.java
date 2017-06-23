/**
 * DigitalTown SSO API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class UsersEdit {
  
  @SerializedName("first_name")
  private String firstName = null;
  @SerializedName("last_name")
  private String lastName = null;
  @SerializedName("userID")
  private String userID = null;

  /**
   * First Name
   **/
  @ApiModelProperty(required = true, value = "First Name")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Last Name
   **/
  @ApiModelProperty(required = true, value = "Last Name")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * User ID
   **/
  @ApiModelProperty(required = true, value = "User ID")
  public String getUserID() {
    return userID;
  }
  public void setUserID(String userID) {
    this.userID = userID;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsersEdit usersEdit = (UsersEdit) o;
    return (this.firstName == null ? usersEdit.firstName == null : this.firstName.equals(usersEdit.firstName)) &&
        (this.lastName == null ? usersEdit.lastName == null : this.lastName.equals(usersEdit.lastName)) &&
        (this.userID == null ? usersEdit.userID == null : this.userID.equals(usersEdit.userID));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.firstName == null ? 0: this.firstName.hashCode());
    result = 31 * result + (this.lastName == null ? 0: this.lastName.hashCode());
    result = 31 * result + (this.userID == null ? 0: this.userID.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersEdit {\n");
    
    sb.append("  firstName: ").append(firstName).append("\n");
    sb.append("  lastName: ").append(lastName).append("\n");
    sb.append("  userID: ").append(userID).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
