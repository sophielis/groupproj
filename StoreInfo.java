import java.util.*;
public class StoreInfo {

 private String user;
 private String pass;

 public StoreInfo(String user, String pass) {
  checkInfo(user, pass);
  this.user = user;
  this.pass = pass;
 }

 public String getUser() {
  return user;
 }

 public String getPass() {
  return pass;
 }

 public static boolean checkInfo(String user, String pass) {
  if (user.length() >= 5 && user.length() <= 8 && pass.length() >= 5 && pass.length() <= 8) {
   return true;
  } else {
   return false;
  }
 }

 public String toString(){
   return "Login information: Username =  " + user  + " Password =  " + pass;
 }

}
