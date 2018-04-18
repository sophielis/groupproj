import java.util.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.*;

public class Data {

 private static HashMap < String, ArrayList<String>> database = new HashMap < String, ArrayList<String>> ();

 public static void addUser(StoreInfo s) {
  database.put(s.getUser(), new ArrayList <String> () );
  database.get(s.getUser()).add(s.getPass());

 }

 public static void writeResults(String memo,String user, String pass) {
  BufferedWriter output = null;
  try {
   File file = new File("results.txt");
   output = new BufferedWriter(new FileWriter(file, true));
   BufferedReader br = new BufferedReader(new FileReader(file));
   String st;
   while ((st = br.readLine()) != null) {}
   output.write(user + " ");
   output.write(pass + " ");
   output.write(String.valueOf(memo).replaceAll(" ", "_") + " ");
   output.newLine();
  } catch (IOException e) {
  } finally {
   if (output != null) {
    try {
     output.close();
    } catch (Exception e) {
    }
   }
  }
 }

 public static void init() {
  File file = new File("info.txt");
  try {
   BufferedReader br = new BufferedReader(new FileReader(file));
   String st;
   while ((st = br.readLine()) != null) {
    String[] split = st.split(" ");
    StoreInfo store = new StoreInfo(split[0], split[1]);
    addUser(store);
   }
  } catch (Exception e) {}
 }

 public static String readFile(String user, String pass) {
  File file = new File("results.txt");
  String prev_memo = "";
  try {
   BufferedReader br = new BufferedReader(new FileReader(file));
   String st;
   while ((st = br.readLine()) != null) {
    if (st.contains(user + " " + pass)) {
     String[] res = st.split(" ");
     prev_memo += res[2]+"<br>";

    }
   }
  } catch (Exception e) {

  }
  return prev_memo.replaceAll("_", " ");
 }

 public static boolean checkUser(String user, String pass) {
  String result = "";
   if (database.get(user)!=null&&database.get(user).get(0).equals(pass)) {
    return true;
   }
  return false;
 }


 public static void writeTo(StoreInfo s) {
  BufferedWriter output = null;
  try {
   File file = new File("info.txt");
   output = new BufferedWriter(new FileWriter(file, true));
   BufferedReader br = new BufferedReader(new FileReader(file));
   String st = "";

   output.write(s.getUser() + " ");
   output.write(s.getPass());
   output.newLine();

  } catch (IOException e) {
  } finally {
   if (output != null) {
    try {
     output.close();
    } catch (Exception e) {
    }
   }
  }
 }

 public static void removeMemo(String curr_user, String curr_pass) {
  database.put(curr_user, new ArrayList<String>(Arrays.asList(curr_pass)));
  File inputFile = new File("results.txt");
File tempFile = new File("myTempFile.txt");
try{
BufferedReader reader = new BufferedReader(new FileReader(inputFile));
BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

String lineToRemove = curr_user+" "+curr_pass;
String currentLine;
while((currentLine = reader.readLine()) != null) {
    String trimmedLine = currentLine.trim();
    if(trimmedLine.contains(lineToRemove)) continue;
    writer.write(currentLine + System.getProperty("line.separator"));
}
writer.close();
reader.close();
boolean successful = tempFile.renameTo(inputFile);
}
catch(Exception e){

}
try{
InputStream in = new FileInputStream(tempFile);
      OutputStream out = new FileOutputStream(inputFile);
      byte[] buf = new byte[1024];
      int len;

      while ((len = in.read(buf)) > 0) {
         out.write(buf, 0, len);
      }
      in.close();
      out.close();
      BufferedReader in1 = new BufferedReader(new FileReader(inputFile));
      String str;

      while ((str = in1.readLine()) != null) {
      }
      in.close();
    }
    catch(Exception e){

    }
   }
}
