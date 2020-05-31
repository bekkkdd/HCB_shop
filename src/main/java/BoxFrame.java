import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BoxFrame extends JFrame {

    ArrayList<JButton> goodsButton = new ArrayList<JButton>();
    JButton exit;

    JLabel error = new JLabel();

    boolean toSiren = false;

    ArrayList<Good> goods = new ArrayList<Good>();
    ArrayList<Good> goodsList = new ArrayList<Good>();

    JTextArea goodStrings = new JTextArea();
    double sum = 0;

    double correctMass = 0;
    double factMass = 0;


    BoxFrame(final Customer customer) {

        Server.makeCustomerBoxActive(customer, true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Box for " + customer.getName() + " " + customer.getSurname());
        setSize(500, 500);
        setLayout(null);

        try {
            goods = Database.getAllGoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int x = 20;
        int y = 20;


        for (int i = 0; i < goods.size(); i++) {
            JButton goodButton = new JButton(goods.get(i).getName());

            goodButton.setSize(200, 50);
            goodButton.setLocation(x, y);
            final int finalI = i;
            goodButton.setText(goods.get(i).getName());
            goodButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    error.setText("");
                    if (goods.get(finalI).getAgeLimit() - customer.getAge() <= 0) {
                        if (sum + goods.get(finalI).getPrice() < customer.getBankAccount()) {
                            correctMass += goods.get(finalI).getWeight();
                            if (factMass - correctMass < goods.get(finalI).getWeight() / 2) {
                                goodsList.add(goods.get(finalI));
                                fillHistory();
                            } else {
                                error.setText("You are going to steal !!! ");
                                toSiren = true;
                            }
                        } else {
                            error.setText("Not enough money =(");
                        }
                    } else {
                        error.setText("You are too young =P");
                    }
                }
            });

            goodsButton.add(goodButton);

            add(goodButton);
            y += 50;

            goods.get(i);
        }

        for (JButton gb : goodsButton) {
            add(gb);
        }

        goodStrings.setLocation(230, 100);
        goodStrings.setSize(300, 400);
        goodStrings.setEditable(false);
        add(goodStrings);

        error.setLocation(250, 20);
        error.setSize(200, 40);
        add(error);

        exit = new JButton("Exit --- PAY");
        exit.setSize(100, 50);
        exit.setLocation(20, y);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Server.pay(customer, sum);
                Server.makeCustomerBoxActive(customer, false);
                dispose();
            }
        });
        add(exit);

    }

    public void fillHistory() {
        goodStrings.setText("");
        sum = 0;
        for (int i = 0; i < goodsList.size(); i++) {
            goodStrings.append(goodsList.get(i).getName() + "\n");
            sum += goodsList.get(i).getPrice();
        }

        goodStrings.append("Total: " + sum + "\n");
    }
}
