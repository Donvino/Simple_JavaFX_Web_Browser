/*
 * This programm uses JavaFx library dev. by Oracle so you should open it with Netbeans(it will have the javaFX libraries in it or you can try execute the dist file
 * Made by North People for North People
 */
package webviewsample;

import java.awt.List;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.*;
import javafx.stage.Stage;
 
 
public class WebViewSample extends Application {
  private WebEngine myWebEngine;
    List BrowserHistory;
    int currentPage=0;
    int lastpage_number=0;
  public void start(Stage stage) {
    stage.setTitle("North People Browser For Real Deers");
    BrowserHistory=new List();
    final TextField addressBar = new TextField();
    final Button back=new Button();
    final Button next=new Button();
    back.setText("Back");
    next.setText("Next");
    addressBar.setText("http://");
    addressBar.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        myWebEngine.load(addressBar.getText());
        BrowserHistory.add(addressBar.getText());
        lastpage_number++;
        currentPage=lastpage_number;
      }
    });
    back.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        if(currentPage>1)
                    {
                        System.out.println(currentPage);
                        System.out.println(BrowserHistory.getItem(currentPage-2));
                        myWebEngine.load(BrowserHistory.getItem(currentPage-2));
                        addressBar.setText(BrowserHistory.getItem(currentPage-2));
                        currentPage=currentPage-1;
                        
                    }
      }
    });
    next.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        if(currentPage<lastpage_number)
                    {
                        System.out.println(currentPage);
                        System.out.println(BrowserHistory.getItem(currentPage));
                        myWebEngine.load(BrowserHistory.getItem(currentPage));
                        addressBar.setText(BrowserHistory.getItem(currentPage));
                        currentPage=currentPage+1;
                    }
      }
    });

    WebView myBrowser = new WebView();
    myWebEngine = myBrowser.getEngine();
    myWebEngine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
      @Override public void changed(ObservableValue<? extends Throwable> observableValue, Throwable oldException, Throwable exception) {
        System.out.println("WebView encountered an exception loading a page: " + exception);
      }
    });

    VBox root = new VBox();
    root.getChildren().setAll(
        addressBar,back,next,
        myBrowser
    );
    stage.setScene(new Scene(root));
    stage.show();
  }

  public static void main(String[] args) { launch(args); }
}
