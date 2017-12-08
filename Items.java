import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Items  extends Application{

	TableView<Product> table;
	TextField nameInput,phoneInput,idInput;
	ObservableList<Product> product;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Application.launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	
		
		BorderPane pane =new BorderPane();
		Button items=new Button("Items");
		Button Suppliers=new Button("Suppliers");
		Button sold=new Button("SoldItems");
		
		Label l=new Label("HD Market");
		//l.setFont("");
		
		// name column
		TableColumn<Product, String> supName =new TableColumn<>("Name");
		supName.setMinWidth(200);
		supName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//phoneNum column
		TableColumn<Product, Integer> supPhone =new TableColumn<>("Phone Number");
		supPhone.setMinWidth(150);
		supPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		//sid column
		TableColumn<Product, Integer> supId =new TableColumn<>("sId");
		supId.setMinWidth(150);
		supName.setCellValueFactory(new PropertyValueFactory<>("sid"));
		
		table=new TableView<>();
		table.setItems(getProduct());
		table.getColumns().addAll(supName,supPhone,supId);
		
		 nameInput = new TextField("name");
					//nameInput.setPromptText("");
		 phoneInput = new TextField("phone");
		 idInput = new TextField("id");
		
		 Button  addB=new Button("Add");
		 addB.setOnAction(e1-> addButtonClicked());
		 Button delete=new Button("Delete");
		
		
		HBox hb=new HBox(20);
		hb.getChildren().addAll(items,Suppliers,sold);
		pane.setTop(hb);
		VBox vb=new VBox();
		vb.getChildren().add(table);
		
		 
		 HBox hb1=new HBox(10);
		 hb1.setPadding(new Insets(10,10,10,10));
		 hb1.setSpacing(10);
		 hb1.getChildren().addAll(nameInput,phoneInput,idInput,addB,delete);
		 
		 //supp's relations
		Suppliers.setOnAction(e1 ->{
			
			 pane.setBottom(hb1);
			 try{
			 Menu db = new Menu();
				String name = nameInput.getText();
				int phoneNumber = Integer.parseInt(phoneInput.getText());
				int id = Integer.parseInt(idInput.getText());
				
				String sql = "Insert into hd_market(name, phoneNumber,sid) values('" +
				name + "', " + phoneNumber + "', " +id   + ")";
				db.write(sql);
				refresh();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pane.setTop(vb);
			
		});
		
		Scene s=new Scene(pane,400,500);
		arg0.setScene(s);
		arg0.show();
		refresh();
		
	}
	//to add any field into columns
	public void addButtonClicked(){
		Product products=new Product();
		products.setName(nameInput.getText());
		products.setPhoneNumber(Integer.parseInt(phoneInput.getText()));
		products.setSid(Integer.parseInt(idInput.getText()));
		table.getItems().add(products);
		((List<Product>) nameInput).clear();
		((List<Product>) phoneInput).clear();
		((List<Product>) idInput).clear();
	}
	
	//to delete any field 
public void deleteClickedButton(){
	ObservableList<Product> productSelected,allProduct;
	allProduct=table.getItems();
	productSelected=table.getSelectionModel().getSelectedItems();
	productSelected.forEach(allProduct::remove);
}
	
	public ObservableList<Product> getProduct(){
	 product=FXCollections.observableArrayList();
		//product.add(new Product(name, phoneNumber, sid))
		return product;
	}
	
	private void refresh() {
		try {
			Menu db = new Menu();
			String sql = "Select * from hd_market";
			
			ResultSet result = db.read(sql);
			Object items = "";
			while(result.next()){
				items+= result.getString(1) + "\t" + result.getString(2) + 
						"\t" + result.getString(3) + "\n";
			}
		//	txtArea.setText(emps);
//			product.add(new Product(
//					result.getString("name"),
//					result.getInt(2),
//					result.getInt(3)
//					
//					));
			product.add((Product)items);
			table.setItems(product);
			
			db.connection.close();
			db.connection = null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

}
