/*
  XuMoQi - word game program
  Copyright (C) 2014  Jeff Pace

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along
  with this program; if not, write to the Free Software Foundation, Inc.,
  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

  See https://raw.github.com/jpace/xumoqi/master/LICENSE for the full license.

  The full source code for this program is available at:
  http://github.com/jpace/xumoqi

  This program includes code from the GPL'd program:
  http://sourceforge.net/projects/scrabbledict/
*/

package org.incava.xumoqi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        NumberPicker np = getNumberPicker();
        np.setMaxValue(Util.type ? 8 : 3);
        np.setMinValue(2);

        Spinner gameTypeSpinner = getGameTypeSpinner();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, Util.type ? R.array.pro_game_types : R.array.free_game_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameTypeSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private NumberPicker getNumberPicker() {
    	return (NumberPicker)findViewById(R.id.wordLengthNumberPicker);
    }
    
    private Spinner getGameTypeSpinner() {
    	return (Spinner)findViewById(R.id.gameTypeSpinner);
    }
    
    public void onClickStart(View view) {
    	Intent intent = new Intent(this, QueryActivity.class);
    	NumberPicker np = getNumberPicker();
        
        Spinner gameTypeSpinner = getGameTypeSpinner();
        String gameType = gameTypeSpinner.getSelectedItem().toString();
        GameParams gp = new GameParams(np.getValue(), gameType);
        intent.putExtra(Constants.GAME_PARAMS, gp);
        
    	startActivity(intent);
    }
}
