package com.example.apnakissan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class LocalModel {

    private static final String TAG = "LocalModel";
    private static final float SIMILARITY_THRESHOLD = 0.95f;  // 90%

    private String[] diseaseNames = {
            "Apple Blackrot", "Bacterial Leaf", "Black Measles",
            "Blight Disease", "Blue Barry","Brown_Spot","Cherry Sour","Common_Rust_Corn","Corn_Cercopora","GrapeBlackroot", "Early Blight Potato","Healthy Appl"
    };

    private String[] diseaseSolutions = {
            "Solution: Use fungicides and remove infected leaves.",
            "Solution: Use sulfur or baking soda treatment.",
            "Solution: Use resistant varieties and fungicides.",
            "Solution: Improve drainage and avoid overwatering.",
            "Solution: Remove infected plants and improve soil drainage.",
            "Solution: Check for nutrient deficiencies or pests.",
            "Solution: Use fungicides and remove infected leaves.",
            "Solution: Use sulfur or baking soda treatment.",
            "Solution: Use resistant varieties and fungicides.",
            "Solution: Improve drainage and avoid overwatering.",
            "Solution: Remove infected plants and improve soil drainage.",
            "Solution: Check for nutrient deficiencies or pests."
    };

    public String detectDisease(Bitmap uploadedImage, Context context) {
        for (int i = 0; i < diseaseNames.length; i++) {
            String imageName = diseaseNames[i].toLowerCase().replace(" ", "_") + ".jpg";
            Bitmap diseaseImage = loadImageFromAssets(context, imageName);

            if (diseaseImage != null) {
                float similarity = calculateColorSimilarity(uploadedImage, diseaseImage);
                if (similarity >= SIMILARITY_THRESHOLD) {
                    return diseaseNames[i] + "\n" + diseaseSolutions[i];
                }
            }
        }
        return "No matching disease found.";
    }

    private Bitmap loadImageFromAssets(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        try (InputStream inputStream = assetManager.open("diseases/" + fileName)) {
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.e(TAG, "Error loading image: " + fileName, e);
            return null;
        }
    }

    // Calculate similarity using average color difference
    private float calculateColorSimilarity(Bitmap bitmap1, Bitmap bitmap2) {
        int width = Math.min(bitmap1.getWidth(), bitmap2.getWidth());
        int height = Math.min(bitmap1.getHeight(), bitmap2.getHeight());

        Bitmap resizedBitmap1 = Bitmap.createScaledBitmap(bitmap1, width, height, false);
        Bitmap resizedBitmap2 = Bitmap.createScaledBitmap(bitmap2, width, height, false);

        long totalDiff = 0;
        int totalPixels = width * height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel1 = resizedBitmap1.getPixel(x, y);
                int pixel2 = resizedBitmap2.getPixel(x, y);

                int diffR = Math.abs((pixel1 >> 16) & 0xff - (pixel2 >> 16) & 0xff);
                int diffG = Math.abs((pixel1 >> 8) & 0xff - (pixel2 >> 8) & 0xff);
                int diffB = Math.abs((pixel1) & 0xff - (pixel2) & 0xff);

                totalDiff += (diffR + diffG + diffB) / 3;  // Average color difference
            }
        }

        float avgDiff = totalDiff / (float) totalPixels;
        return 1.0f - (avgDiff / 255);  // Normalize to a 0-1 scale
    }
}
