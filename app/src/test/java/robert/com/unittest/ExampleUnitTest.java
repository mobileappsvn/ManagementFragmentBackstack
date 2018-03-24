package robert.com.unittest;

import org.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private class User {

        public String name;
        public String fullname;

        public User(String name, String fullname) {
            this.name = name;
            this.fullname = fullname;
        }

        @Override
        public String toString() {
            return name + "->" + fullname;
        }
    }
    @Test
    public void addition_isCorrect() throws Exception {
// Create a LinkedList
        LinkedList<String> linkedlist = new LinkedList<String>();

        // Add elements to LinkedList
        linkedlist.add("Item1");
        linkedlist.add("Item2");
        linkedlist.add("Item3");
        linkedlist.add("Item4");
        linkedlist.add("Item5");
        linkedlist.add("Item6");
        linkedlist.add("Item7");

        // Displaying LinkedList elements
        System.out.println("LinkedList elements:");
        Iterator it= linkedlist.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // Obtaining Sublist from the LinkedList
        List sublist = linkedlist.subList(2,5);
        System.out.println("Result=" + sublist);

        // Displaying SubList elements
        System.out.println("\nSub List elements:");
        Iterator subit= sublist.iterator();
        while(subit.hasNext()){
            System.out.println(subit.next());
        }

    /* Any changes made to Sub List will be reflected
     * in the original List. Lets take this example - We
     * are removing element "Item4" from sublist and it
     * should be removed from original list too. Observe
     * the Output of this part of the program.
     */
        sublist.remove("Item4");
        System.out.println("\nLinkedList elements After remove:");
        Iterator it2= linkedlist.iterator();
        while(it2.hasNext()){
            System.out.println(it2.next());
        }


        //================================//


        /*List<User> userList = new ArrayList<>();
        userList.add(new User("1", "Hoang FullName 1"));
        userList.add(new User("11", "Hoang FullName 11"));
        userList.add(new User("33", "Hoang FullName 33"));
        userList.add(new User("2", "Hoang FullName 2"));
        userList.add(new User("3", "Hoang FullName 3"));
        userList.add(new User("33", "Hoang FullName 33"));


        for (User user:userList) {
            System.out.println(user.toString());
        }
        JSONArray jarr = new JSONArray();
        Iterator<User> userIte = userList.iterator();
        while (userIte.hasNext()) {
            User user = userIte.next();
            //System.out.println(user.toString());
            jarr.put(user.toString());
        }
        System.out.print(jarr.toString());*/
    }
}