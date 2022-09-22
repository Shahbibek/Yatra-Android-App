package com.example.yatra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerItemInCartAdapter extends RecyclerView.Adapter<RecyclerItemInCartAdapter.ViewHolder> {
    Context context;
    ArrayList<RecyclerItemInCartModel> arrayList;
    int totalProduct;

    public RecyclerItemInCartAdapter(Context context, ArrayList<RecyclerItemInCartModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_in_cart, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<ImageButton> ratingButtons = new ArrayList<ImageButton>();


        holder.imageProduct.setImageResource(arrayList.get(position).getImg());
        holder.productTitle.setText(arrayList.get(position).getTitle());
        holder.productPrice.setText(Integer.toString(arrayList.get(position).getPrice()));
        holder.deliveryDate.setText(arrayList.get(position).getDeliveryDate());
        holder.deliveryMode.setText(arrayList.get(position).getDeliveryMode());

        ratingButtons.add(holder.rating1);
        ratingButtons.add(holder.rating2);
        ratingButtons.add(holder.rating3);
        ratingButtons.add(holder.rating4);
        ratingButtons.add(holder.rating5);

        for(ImageButton rattingButton: ratingButtons) {
            rattingButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
//                    index++;
                    ImageButton clickedRating = (ImageButton) view;
                    int clickedRatingTag = Integer.parseInt(view.getTag().toString());
                    updateRating(clickedRatingTag, ratingButtons);
//                    Toast.makeText(SingleProductPage.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //To increase quantity of the product
        holder.addProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                totalProduct++;
                holder.productQuantity.setText("" + totalProduct);
            }
        });
        //To decrease quantity
        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(totalProduct > 1)
                    totalProduct--;
                holder.productQuantity.setText(""+totalProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void updateRating(int tag, List<ImageButton> ratingButtons){
        // A function to update ratings
        int index = 1;
        for (ImageButton rattingButton :
                ratingButtons) {
            if(index <= tag){
                rattingButton.setImageResource(R.drawable.ic_baseline_star_rate_24);
            }else{
                rattingButton.setImageResource(R.drawable.ic_baseline_star_outline_24);
            }
            index++;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton rating1, rating2, rating3, rating4, rating5, removeProduct, addProduct;
        ImageView imageProduct;
        TextView productTitle, productPrice,productDescription, productQuantity, deliveryDate, deliveryMode;
        Button btnAddToCart;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            deliveryDate = itemView.findViewById(R.id.deliveryDate);
            deliveryMode = itemView.findViewById(R.id.deliveryMode);
            addProduct = itemView.findViewById(R.id.addProduct);
            productDescription = itemView.findViewById(R.id.productDescription);
            removeProduct = itemView.findViewById(R.id.removeProduct);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            btnAddToCart = itemView.findViewById(R.id.addToCartBtn);

            rating1 = itemView.findViewById(R.id.rating1);
            rating2 = itemView.findViewById(R.id.rating2);
            rating3 = itemView.findViewById(R.id.rating3);
            rating4 = itemView.findViewById(R.id.rating4);
            rating5 = itemView.findViewById(R.id.rating5);
        }
    }
}
