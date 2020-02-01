package shared.turboeditor.home;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import shared.turboeditor.R;
import shared.turboeditor.home.texteditor.KeyBoardEditText;
import shared.turboeditor.home.texteditor.PageSystem;
import shared.turboeditor.util.IHomeActivity;
import shared.turboeditor.views.GoodScrollView;

public class EditorActivity extends Activity implements IHomeActivity, PageSystem.PageSystemInterface {

    private KeyBoardEditText editor;
    private KeyboardView kbView;
    private LinearLayout layout;
    public static GoodScrollView verticalScroll;
    public static PageSystem pageSystem;

    @Override
    protected void onStart() {
        super.onStart();
    }
    private Handler updateHandler=new Handler();
    private Runnable cr=new Runnable() {
        @Override
        public void run() {
            editor.replaceTextKeepCursor(null);
        }
    };
    void updateTextSyntax() {
        updateHandler.removeCallbacks(cr);
        updateHandler.removeCallbacks(cr);
        updateHandler.postDelayed(cr, 1500);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        verticalScroll=findViewById(R.id.vertical_scroll);

        editor=findViewById(R.id.editor);
        editor.setupEditor();
        kbView=findViewById(R.id.view_keyboard);
        layout=findViewById(R.id.layout_main);
        editor.setKeyboardType(layout,kbView,true);



        showEditor();

        pageSystem=new PageSystem(this,this,editor.getText().toString());
    }

    private void showEditor(){
        editor.resetVariables();
        editor.disableTextChangedListener();
        editor.replaceTextKeepCursor("");
        editor.enableTextChangedListener();
    }

    @Override
    public boolean showInterstitial() {
        return false;
    }

    @Override
    public void onPageChanged(int page) {

    }
}
