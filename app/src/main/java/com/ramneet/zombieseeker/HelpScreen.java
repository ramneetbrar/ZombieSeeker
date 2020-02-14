package com.ramneet.zombieseeker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpScreen extends AppCompatActivity {
//
//    TextView hyperLink;
//    Spanned text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        TextView authorsHyperlink = findViewById(R.id.aboutAuthors);
        Spanned Text = Html.fromHtml("About the authors: Our names are Ramneet Brar and " +
                "Riya Dhariwal and we are students attending Simon Fraser University. We are " +
                "enrolled in the course CMPT 276, Introduction to Software Engineering, and as an " +
                "assignment, we have created this fun zombie app for people to play with !  <br />" +
                "<a href='http://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home'>Course Website</a>");

        authorsHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        authorsHyperlink.setText(Text);

        TextView citationOneHyperlink = findViewById(R.id.citation);
        Text = Html.fromHtml("<a href='https://i.pinimg.com/originals/5c/cf/da/5ccfdaddef13b6dcd7122f20e6f551f1.gif'>Zombie GIF</a>");

        citationOneHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        citationOneHyperlink.setText(Text);

        TextView citationTwoHyperlink = findViewById(R.id.citation2);
        Text = Html.fromHtml( "<a href='https://cdn.hipwallpaper.com/i/31/45/V2zRmh.jpg'>Grey Background Image</a>");

        citationTwoHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        citationTwoHyperlink.setText(Text);

        TextView citationThreeHyperlink = findViewById(R.id.citation3);
        Text = Html.fromHtml( "<a href='https://cdn.imgbin.com/12/21/25/imgbin-cartoon-zombie-zombie-KnZgwiBDaSSM4EHi72XqdSCU3.jpg'>Green Zombie</a>");

        citationThreeHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        citationThreeHyperlink.setText(Text);

//        TextView citationOneHyperlink = findViewById(R.id.citation1);
//        Text = Html.fromHtml("Zombie GIF link  <br />" +
//                "<a href='https://i.pinimg.com/originals/5c/cf/da/5ccfdaddef13b6dcd7122f20e6f551f1.gif'>Zombie GIF</a>");
//
//        citationOneHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
//        citationOneHyperlink.setText(Text);
//
//        TextView citationTwoHyperlink = findViewById(R.id.citation2);
//        Text = Html.fromHtml("Background image link  <br />" +
//                "<a href='https://cdn.hipwallpaper.com/i/31/45/V2zRmh.jpg'>Grey Background Image</a>");
//
//        citationTwoHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
//        citationTwoHyperlink.setText(Text);
//
//        TextView citationThreeHyperlink = findViewById(R.id.citation3);
//        Text = Html.fromHtml("Background image link  <br />" +
//                "<a href='https://cdn.imgbin.com/12/21/25/imgbin-cartoon-zombie-zombie-KnZgwiBDaSSM4EHi72XqdSCU3.jpg'>Green Zombie</a>");
//
//        citationThreeHyperlink.setMovementMethod(LinkMovementMethod.getInstance());
//        citationThreeHyperlink.setText(Text);

    }
}
