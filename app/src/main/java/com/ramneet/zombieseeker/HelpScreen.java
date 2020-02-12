package com.ramneet.zombieseeker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpScreen extends AppCompatActivity {

    TextView hyperLink;
    Spanned text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        hyperLink = findViewById(R.id.aboutAuthors);
        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());

        hyperLink = findViewById(R.id.aboutAuthors);
        text = Html.fromHtml("About the authors: Our names are Ramneet Brar and Riya Dhariwal " +
                "and we are students attending Simon Fraser University. We are enrolled in the course CMPT 276, Introduction to Software Engineering. <br />" +
                "<a href= 'https://www.opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home'>CMPT276_homePage.ca</a>" );

        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink.setText(text);
    }
}
