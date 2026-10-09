package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
  private Context context;
  private LayoutInflater mInflater;
  private List<Article> articles;

  public ArticleViewAdapter(Context context, List<Article> articles) {
    this.context = context;
    this.mInflater = LayoutInflater.from(context);
    this.articles = articles;
  }

  public void update(List<Article> articles){
    this.articles = articles;
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View customView = mInflater.inflate(R.layout.item_article, parent, false);
    return new ArticleViewHolder(customView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article currentArticle = articles.get(position);
    holder.getTxtTitle().setText(currentArticle.getTitle());
    holder.getTxtContent().setText(currentArticle.getContent());

    // Dùng Glide để load ảnh từ URL vào ImageView
    String imageUrl = currentArticle.getImageUrl();
    if (imageUrl != null && !imageUrl.isEmpty()) {
      Glide.with(context)
              .load(imageUrl)
              .placeholder(android.R.drawable.ic_menu_gallery) // Ảnh hiển thị tạm trong lúc chờ tải
              .error(android.R.drawable.ic_menu_report_image)    // Ảnh hiển thị nếu link lỗi
              .into(holder.getImgArticle());
    } else {
      holder.getImgArticle().setImageResource(android.R.drawable.ic_menu_gallery);
    }
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }
}