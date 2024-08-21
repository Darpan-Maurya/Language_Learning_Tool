import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.RotateTransition;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.Group;
import javafx.scene.transform.Translate;
import javafx.animation.Interpolator;

public class BackgroundImageExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Load the background image
        primaryStage.getIcons().add(new Image("C:\\icon1.png"));
        Image backgroundImage = new Image("C:\\Screenshot (168).png");

        // Create a background with the image
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

        // Create a stack pane for the background
        StackPane backgroundPane = new StackPane();
        backgroundPane.setBackground(new Background(background));

        // Create the text nodes
        Text text1 = new Text("Language Learning");
        text1.setFill(Color.BLACK);
        text1.setStyle("-fx-font-size: 48; -fx-font-family: Algerian;");
        Text text2 = new Text("Make a better learning experience");
        text2.setFill(Color.BLACK);
        text2.setStyle("-fx-font-size: 24; -fx-font-family: 'Brush Script MT';");

        // Apply a drop shadow effect to the text
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetY(14); // Adjust the offset for a 3D effect
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.4)); // Set shadow color and transparency
        text1.setEffect(dropShadow);
        text2.setEffect(dropShadow);

        // Create a VBox to stack the text nodes vertically
        VBox vbox = new VBox();
        vbox.getChildren().addAll(text1, text2);
        vbox.setAlignment(Pos.CENTER); // Align the VBox contents to the center
        vbox.setSpacing(35); // Set spacing between the text nodes

        // Create a ComboBox for the dropdown menu
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Register", "Login");
        comboBox.setStyle("-fx-background-color: transparent; -fx-color: white;"); // Set transparent background

        // Create a "Click" button
        Button clickButton = new Button("Click");
        clickButton.setStyle("-fx-background-color: transparent;"); // Set transparent background for the button

        // Create a StackPane to stack the "Click" button and the ComboBox
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_RIGHT); // Align the stack pane to the top right
        stackPane.getChildren().addAll(clickButton, comboBox);

        // Set the size of the "Click" button to occupy 70% of the ComboBox's width and height
        clickButton.prefWidthProperty().bind(comboBox.widthProperty().multiply(0.7));
        clickButton.prefHeightProperty().bind(comboBox.heightProperty().multiply(0.7));
        
        // Load the infinity symbol image
        Image infinityImage = new Image("C:\\donut1.png");

        // Create an ImageView for the infinity symbol
        ImageView infinityImageView = new ImageView(infinityImage);
        infinityImageView.setFitWidth(50); // Set the width of the infinity symbol
        infinityImageView.setPreserveRatio(true); // Preserve the aspect ratio

        // Create a RotateTransition for the infinity symbol to rotate it continuously
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4), infinityImageView);
        rotateTransition.setByAngle(360); // Rotate by 360 degrees
        rotateTransition.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        rotateTransition.setInterpolator(Interpolator.LINEAR); // Linear animation

        // Start the rotation animation
        rotateTransition.play();

        // Add the infinity symbol to a StackPane
        StackPane infinityPane = new StackPane();
        infinityPane.getChildren().add(infinityImageView);

        // Add the StackPane to the VBox
        vbox.getChildren().add(infinityPane);
        
        // Create a stack pane to overlay the text over the background
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundPane, vbox, stackPane);
        
         Image logoImage = new Image("C:\\icon1.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(logoImage.getWidth() * 2); // Enlarge the logo
        logoImageView.setFitHeight(logoImage.getHeight() * 2);

        // Add the logo to the stack pane
        root.getChildren().add(logoImageView);
        StackPane.setAlignment(logoImageView, Pos.TOP_LEFT); 
        
        
        // Create a scene with the stack pane as the root node
        Scene scene = new Scene(root, 650, 500);

        comboBox.setOnAction(e -> {
            String selectedItem = comboBox.getSelectionModel().getSelectedItem();
            if ("Login".equals(selectedItem)) {
                // Execute the login action
                LanguageLearningTool obj = new LanguageLearningTool();
                obj.maain(); // Assuming this method exists in your LanguageLearningTool class
                primaryStage.close(); // Close the JavaFX stage
            }
        });
        
        // Set the scene and show the stage
        primaryStage.setTitle("Language Learning Tool");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create a fade transition for the "Language Learning" text
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), text1);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        
        Timeline timeline = new Timeline();
        for (int i = 0; i < text2.getText().length(); i++) {
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2), new KeyValue(text2.textProperty(), text2.getText().substring(0, i + 1)));
                timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();

        //FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(8), text2);
        //fadeTransition2.setFromValue(0.0);
        //fadeTransition2.setToValue(1.0);
        //fadeTransition2.play();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
