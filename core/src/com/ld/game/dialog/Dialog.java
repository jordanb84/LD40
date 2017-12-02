package com.ld.game.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.text.Text;

/** Thrown together, not exactly elegant but it works **/
public class Dialog {

    private String[] currentDialog;

    private int dialogIndex;

    private boolean displayingDialog;

    private Sprite dialogSprite;

    private int shownLetters;

    private float letterElapsed;

    private boolean canChange;

    private float elapsed;

    private String text;

    private boolean showingText;

    private float textElapsed;

    private String name;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    public Dialog() {
        this.dialogSprite = new Sprite(new Texture(Gdx.files.internal("ui/dialog.png")));

        this.batch = new SpriteBatch();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void showText(String text) {
        this.text = (text);

        this.textElapsed = 0;

        this.showingText = (true);
    }

    public void startWithDialog(String name, String ... pages) {
        this.currentDialog = (pages);

        this.displayingDialog = true;

        this.dialogIndex = 0;

        this.name = (name);

        this.textElapsed = 100;
    }

    public void render() {
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        try {
            if(this.displayingDialog) {
                this.dialogSprite.draw(batch);
                String displayChars = ("");

                for(int i = 0; i < this.shownLetters; i++) {
                    try {
                        displayChars += (this.currentDialog[this.dialogIndex].charAt(i));
                    }catch(Exception e) {

                    }
                }

                if(this.shownLetters >= this.currentDialog[this.dialogIndex].length()) {
                    this.elapsed = (elapsed + 1 * Gdx.graphics.getDeltaTime());

                    if(this.elapsed > 3) {
                        this.elapsed = 0;
                        this.shownLetters = 0;
                        this.dialogIndex++;

                        if(this.dialogIndex >= this.currentDialog.length) {
                            this.displayingDialog = false;
                        }
                    }
                }

                int cap = 32;

                if(this.shownLetters >= cap) {
                    String lineOne = ("");
                    String lineTwo = ("");

                    char[] chars = (this.currentDialog[this.dialogIndex].toCharArray());

                    for(int i = 0; i < this.shownLetters; i++) {
                        if(i <= cap) {
                            lineOne += (chars[i]);
                        }else {
                            lineTwo += (chars[i]);
                        }
                    }

                    Text.Medium.FONT.draw(this.batch, lineOne, 10, 70);
                    Text.Medium.FONT.draw(this.batch, lineTwo, 10, 50);
                }else {
                    Text.Medium.FONT.draw(this.batch, displayChars, 10, 70);
                }

                Text.Medium.FONT.draw(this.batch, "[" + this.name + "]", 10, 115);
            }

            if(this.showingText && !this.displayingDialog) {
                Text.Medium.FONT.setColor(Color.YELLOW);
                Text.Medium.FONT.draw(this.batch, this.text, 30, 115);
                Text.Medium.FONT.setColor(Color.WHITE);
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                this.displayingDialog = false;
                this.dialogIndex = 0;
             }
        }catch(Exception e) {
            e.printStackTrace();
        }

        this.batch.end();
    }

    public void update() {
        try {
            if(this.displayingDialog) {
                this.letterElapsed += 1 * Gdx.graphics.getDeltaTime();

                if(this.letterElapsed > 0.08) {
                    this.letterElapsed = 0;
                    this.shownLetters++;

                    if(this.shownLetters >= this.currentDialog[this.dialogIndex].length()) {
                        this.shownLetters = (this.currentDialog[this.dialogIndex].length());
                    }
                }
            }

            if(!this.displayingDialog) {
                this.textElapsed += (1 * Gdx.graphics.getDeltaTime());

                if(this.textElapsed >= 2.5f) {
                    this.textElapsed = 0;
                    this.showingText = false;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean inProgress() {
        return this.displayingDialog;
    }

}