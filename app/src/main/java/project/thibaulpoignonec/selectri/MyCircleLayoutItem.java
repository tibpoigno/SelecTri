package project.thibaulpoignonec.selectri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import com.example.x.circlelayout.CircleLayout;
import com.example.x.circlelayout.CircularLayoutItem;


public class MyCircleLayoutItem extends CircularLayoutItem {

    ChoixBacActivity layout;

    public MyCircleLayoutItem(Context context, ChoixBacActivity _layout) {
        super(context);initialize();
        layout=_layout;
    }

    public MyCircleLayoutItem(Context context, CircleLayout cl) {
        super(context, cl);
        initialize();
    }

    public MyCircleLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MyCircleLayoutItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public void initialize() {


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                String type;
                int index = getIndex();
                while (index > 5 | index < -5) {
                    if (index < 0 | index < -5) index += 6;
                    if (index > 0 | index > 5) index -= 6;
                }
                if (index < 0) index = 6 + index;
                switch (index) {
                    case 0: {
                        type = "dmr";
                        break;
                    }
                    case 1: {
                        type = "recyclable";
                        break;
                    }
                    case 2: {
                        type = "verre";
                        break;
                    }
                    case 3: {
                        type = "textile";
                        break;
                    }
                    case 4: {
                        type = "pile";
                        break;
                    }
                    case 5: {
                        type = "decheterie";
                        break;
                    }
                    default:
                        type = "erreur";
                }
                Toast.makeText(getContext(), "Afficher les point de collectes " + type, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), "Index :  " + getIndex(), Toast.LENGTH_SHORT).show();

                showMap(type);
            }
        });


        this.setOnFocusListener(new OnFocusListener() {
            @Override
            public void onFocus() {
//                Toast.makeText(getContext(),"Item number: "+getIndex()+ " is now on focus ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnFocus() {

            }
        });
    }

    public void showMap(String type)
    {
        Intent intent = new Intent(layout, MapActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("TYPE",type);
        intent.putExtras(bundle);
        Log.d("Main","launch MapActivity");
        layout.startActivity(intent);
    }




}
