package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URISyntaxException;

public class BattleController {

    private Monster m1, m2;

    private GameController gameController;

    @FXML
    ImageView m1Image, m2Image, firem2, firem1, winwin;
    @FXML
    Button ATK,Heal;
    @FXML
    Label m1Label,m2Label,winning, roundText, turnText, Turn, Round;
    @FXML
    TextField HealInput;
    @FXML
    ProgressBar blood1, blood2;

    @FXML public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    m1Image.setImage(new Image(getClass().getResource(m1.getImgPath()).toURI().toString()));
                    m2Image.setImage(new Image(getClass().getResource(m2.getImgPath()).toURI().toString()));

                    gameController = new GameController(m1, m2);
                }
                catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                blood1.setProgress(1);
                blood2.setProgress(1);
                m1Label.setText(m1.toString());
                m2Label.setText(m2.toString());
                int round1 = gameController.getRound();
                int turn1 = gameController.getTurn();
                roundText.setText(String.valueOf(round1));
                turnText.setText(String.valueOf(turn1));

            }
        });
    }

    public void setMonster1(Monster m){
        this.m1 = m;
    }
    public void setMonster2(Monster m){
        this.m2 = m;
    }

    @FXML public void handleAtkBtnOnAction(ActionEvent event){
        if(gameController.getTurn() == 1 ) {
            //1 set
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> {
                        firem2.setImage(new Image("/images/fire.gif", 200, 150, false, true));
                    }),
                    new KeyFrame(Duration.seconds(1.72), e -> {
                        firem2.setImage(new Image("/images/empty.png", 200, 150, false, true));
                    })
            );
            timeline.play();//monster 2's fire
        }
        else {
            //1 set
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> {
                        firem1.setImage(new Image("/images/fire.gif", 200, 150, false, true));
                    }),
                    new KeyFrame(Duration.seconds(1.72), e -> {
                        firem1.setImage(new Image("/images/empty.png", 200, 150, false, true));
                    })
            );
            timeline.play();//monster 2's fire
        }
        gameController.attackState();
        if (gameController.isWin()){
            winwin.setImage((new Image("/images/win.png")));
            winning.setText(gameController.getWinMonster() + "");
            ATK.setVisible(false);
            Heal.setVisible(false);
            HealInput.setVisible(false);
            Round.setVisible(false);
            Turn.setVisible(false);
            roundText.setVisible(false);
            turnText.setVisible(false);
            this.display();
        }
        else {
            this.display();
        }
    }

    @FXML public void handleHealBtnOnAction(ActionEvent event){
        if(gameController.getTurn() == 1 ) {
            //1 set
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> {
                        firem1.setImage(new Image("/images/heal.gif", 75, 75, false, true));
                    }),
                    new KeyFrame(Duration.seconds(1.72), e -> {
                        firem1.setImage(new Image("/images/empty.png", 75, 75, false, true));
                    })
            );
            timeline.play();//monster 2's fire
        }
        else {
            //1 set
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> {
                        firem2.setImage(new Image("/images/heal.gif", 75, 75, false, true));
                    }),
                    new KeyFrame(Duration.seconds(1.72), e -> {
                        firem2.setImage(new Image("/images/empty.png", 75, 75, false, true));
                    })
            );
            timeline.play();//monster 2's fire
        }
        gameController.healState(HealInput.getText());
        this.display();
    }

    public void display(){
        blood1.setProgress((double) m1.check_hp()/(double) m1.getHp_st());
        blood2.setProgress((double) m2.check_hp()/(double) m2.getHp_st());
        int round1 = gameController.getRound();
        int turn1 = gameController.getTurn();
        roundText.setText(String.valueOf(round1));
        turnText.setText(String.valueOf(turn1));
    }

}
