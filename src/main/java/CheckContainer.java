import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckContainer extends Container {
    JButton enterToStore;
    JLabel errors;

    CheckContainer(){
        setSize(500,500);
        setLayout(null);

        errors = new JLabel("");
        errors.setSize(500,60);
        errors.setLocation(110,100);
        add(errors);

        enterToStore = new JButton("Take Box");
        enterToStore.setSize(200,60);
        enterToStore.setLocation(110,250);
        enterToStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errors.setText("");
                Customer customer = Server.getCustomerByFace(Recognizer.recognize());
                if(customer.getId() == null){
                    errors.setText("Sorry, your face was not found in our DB =(");
                }else if(customer.getActiveBox()){
                    errors.setText("Sorry, you already have a box =(");
                }
                else if(customer.getCrimeCoef() > 0.5){
                    errors.setText("Sorry, you have bad history of crime =(");
                }else if(customer.getBankAccount() / (customer.getCrimeCoef() * 2 + 1) > 5000){
                    errors.setText("Welcome =)");
                    BoxWork boxWork = new BoxWork(customer);
                    Thread thread = new Thread(boxWork);
                    thread.start();
                }else {
                    errors.setText("Sorry, you do not have enough money on your bank account =(");
                }
            }
        });
        add(enterToStore);
    }
}
