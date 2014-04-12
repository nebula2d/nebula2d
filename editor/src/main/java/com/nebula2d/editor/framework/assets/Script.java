/*
 * Nebula2D is a cross-platform, 2D game engine for PC, Mac, & Linux
 * Copyright (c) 2014 Jon Bonazza
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nebula2d.editor.framework.assets;

import com.nebula2d.editor.util.FullBufferedReader;

import java.io.*;

public class Script extends Asset {
    public static final String NEW_SCRIPT_CONTENT = "\n\nfunction start(me) {\n}\n\nfunction update(me, dt){\n}";
    //region members
    protected String content;
    //endregion

    //region constructor
    public Script(String path) {
        super(path);
        init();
    }

    public Script() {
        super("");
    }
    //endregion

    //region internal methods
    protected void init() {
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(path));
            String tmp;
            while((tmp = br.readLine()) != null) {
                sb.append(tmp);
            }

            content = sb.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file at path: " + path);
        } catch (IOException e) {
            System.out.println("Failed to read file at path: " + path);
        }
    }
    //endregion

    //region accessors
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //endregion

    //region interface overrides
    @Override
    public void load(FullBufferedReader fr) throws IOException {
        //Noop!
    }
    //endregion
}
