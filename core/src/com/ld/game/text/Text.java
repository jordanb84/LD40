package com.ld.game.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public enum Text {
    Medium("font/pixel.ttf", 14), Small("font/pixel.ttf", 10), Tiny("font/pixel.ttf", 8)
    ;

    Text(String path, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = (size);

        this.FONT = (generator.generateFont(parameter));
    }

    public BitmapFont FONT;

    public static void draw(Text textType, SpriteBatch batch, String text, int x, int y) {
        textType.FONT.draw(batch, text, x, y);
    }

}