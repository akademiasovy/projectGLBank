package sample.mainUI;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Client;
import sample.Employee;
import sample.database.Database;

import java.util.List;

public class MainController {
    private int index;
    private List<Client> list;
    public Label lblEmployee;
    public ComboBox comboClient;
    public Label lblClient;
    private Employee employee;
    public void setEmplyee(Employee employee) {
        this.employee = employee;
        this.lblEmployee.setText(this.employee.getFirstName()+" "+this.employee.getLastName());
        initComboBoxClient();
    }

    public MainController() {
        index=-1;
    }

    public void initComboBoxClient(){
        Database db=Database.getInstance();
        list= db.getAllClients();
        if(list==null)
            return;
        for(Client client:list){
            if(comboClient!=null){
                comboClient.getItems().add(client.getLname()+" "+client.getFname());
            }
        }
    }

    public void comboChange(ActionEvent actionEvent) {

       index= comboClient.getSelectionModel().getSelectedIndex();
       if(index==-1 || list.size()<index)
           return;
       lblClient.setText(list.get(index).getFname()+" "+list.get(index).getLname());
    }
}
