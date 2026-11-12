package com.hridoy.heartorbit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Invisible hop used by home-screen site icons: opens the site straight in the
 * chosen browser (extra "pkg"), or the phone's default browser if that one is missing.
 */
public class OpenInActivity extends Activity {
  @Override
  protected void onCreate(Bundle saved) {
    super.onCreate(saved);
    Intent in = getIntent();
    Uri d = in == null ? null : in.getData();
    String url = d == null ? null : d.toString();
    String pkg = in == null ? null : in.getStringExtra("pkg");
    if (url != null && (url.startsWith("https://") || url.startsWith("http://"))) {
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
      i.addCategory(Intent.CATEGORY_BROWSABLE);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      boolean opened = false;
      if (pkg != null && !pkg.isEmpty()) {
        try {
          i.setPackage(pkg);
          startActivity(i);
          opened = true;
        } catch (Exception ignored) {
        }
      }
      if (!opened) {
        try {
          i.setPackage(null);
          startActivity(i);
        } catch (Exception ignored) {
        }
      }
    }
    finish();
  }
}
