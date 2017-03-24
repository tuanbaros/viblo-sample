package tuannt.viblosample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<String> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        fakeData();
        setUpRecyclerView();
        showHideWhenScroll();
    }

    private void setUpView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void fakeData() {
        Random random = new Random();
        for (int i = 0; i < 100; i++)
            mList.add(String.valueOf(random.nextInt(100)));
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
            .VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        ListAdapter listAdapter = new ListAdapter(mList);
        mRecyclerView.setAdapter(listAdapter);
    }

    private void showHideWhenScroll() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) mActionButton.hide();
                else mActionButton.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
