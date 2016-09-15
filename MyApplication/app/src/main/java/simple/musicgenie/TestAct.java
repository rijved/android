package simple.musicgenie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import simple.musicgenie.R;

public class TestAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        CloudManager manager = CloudManager.getInstance(this);
        //manager.lazyRequestTrending();
        manager.requestSearch("pawan singh new songs");

        final TextView pad = (TextView) findViewById(R.id.pad);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SectionModel res = DbHelper.getInstance(TestAct.this).getResultList();

                for (ItemModel item : res.getList()) {
                    pad.setText(pad.getText() + item.type + "\n" + item.Title);
                }
            }
        });


    }

}