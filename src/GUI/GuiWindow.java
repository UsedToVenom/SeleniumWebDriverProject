package GUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiWindow extends JFrame {
    private JLabel addressLabel;
    private JLabel passwordLabel;
    private JButton button;
    private JTextField addressField;
    private JTextField passwordField;

    public GuiWindow () {
        setLayout(new FlowLayout());

        addressLabel = new JLabel("Gmail address goes here");
        add(addressLabel);

        addressField = new JTextField(15);
        add(addressField);

        passwordLabel = new JLabel("Password goes here");
        add(passwordLabel);

        passwordField = new JTextField(10);
        add(passwordField);

        button = new JButton("log in!");
        add(button);

        logIn login = new logIn();
        button.addActionListener(login);
    }

        public void run () {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(300, 300);
            this.setVisible(true);
            this.setTitle("Google Logger 1.0 by Venom");
        }


    public class logIn implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String address = addressField.getText();
            String password = passwordField.getText();
            WebDriver driver;
            System.setProperty("webdriver.gecko.driver", "/home/venom/Desktop/IdeaProjects/GoogleLogger/geckodriver/geckodriver");
            driver = new FirefoxDriver();
            driver.get("https://www.google.com/gmail/about/");

            WebElement loginLink;
            loginLink = driver.findElement(By.cssSelector("a.gmail-nav__nav-link:nth-child(2)"));
            loginLink.click();

            WebElement browserAddressField;
            browserAddressField = ((FirefoxDriver) driver).findElementByCssSelector("#identifierId");
            browserAddressField.sendKeys(address);

            WebElement addressButton;
            addressButton = ((FirefoxDriver) driver).findElementByCssSelector("#identifierNext > content:nth-child(3) > span:nth-child(1)");
            addressButton.click();

            //this should be changed to a shorter sleep after assertion
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            WebElement browserPasswordField;
            browserPasswordField = ((FirefoxDriver) driver).findElementByXPath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/form/content/div[1]/div/div[1]/div/div[1]/input");
            browserPasswordField.sendKeys(password);

            WebElement passwordButton;
            passwordButton = ((FirefoxDriver) driver).findElementByCssSelector("#passwordNext > content:nth-child(3) > span:nth-child(1)");
            passwordButton.click();
            //driver.quit();
            addressLabel.setText("things move forward!");
        }
    }
}