package com.pawras.android.basketballclub.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pawras.android.basketballclub.R;
import com.pawras.android.basketballclub.adapters.PopulateListOrders;
import com.pawras.android.basketballclub.adapters.RecyclerItemClickListener;
import com.pawras.android.basketballclub.models.CoachAndPlayer;
import java.util.ArrayList;
import static com.facebook.FacebookSdk.getApplicationContext;

public class Players extends Fragment {
    private ArrayList<CoachAndPlayer> coachList = new ArrayList<>();
    private ProgressDialog loading;
    private RecyclerView recyclerView;
    private PopulateListOrders mAdapter;
    int flag=0;
    String coach_id;
    String shoulOnDataChangeMethodCall="yes";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.players, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listOfOrders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        coach_id = coachList.get(position).getId();
                        openDialog();
                    }
                })
        );
        readData();
        return view;
    }

    public void readData() {
        loading = ProgressDialog.show(getActivity(), "Loading Players", "Please wait..", false, false);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("player");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                coachList.clear();
                Log.d("onDataChange",shoulOnDataChangeMethodCall);
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CoachAndPlayer singleCoach = postSnapshot.getValue(CoachAndPlayer.class);
                    coachList.add(new CoachAndPlayer(singleCoach.getAddress(), singleCoach.getDate_of_birth(), singleCoach.getContact_number(), singleCoach.getEmail(), singleCoach.getSure_name(), singleCoach.getFirst_name(), singleCoach.getId()));
                }
                mAdapter = new PopulateListOrders(coachList);
                recyclerView.setAdapter(mAdapter);
                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //  readData();
        flag++;
        if (flag>1){
            readData();
        }
    }

    public void openDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_warning, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog findMeDialog = dialogBuilder.create();
        findMeDialog.show();
        LinearLayout ok = (LinearLayout) dialogView.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("player").orderByChild("id").equalTo(coach_id);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                            Log.d("match","yes");
                            shoulOnDataChangeMethodCall="no";
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                coachList.clear();
                shoulOnDataChangeMethodCall="yes";
                readData();
                findMeDialog.dismiss();
            }
        });
    }
}