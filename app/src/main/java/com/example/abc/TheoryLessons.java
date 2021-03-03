package com.example.abc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TheoryLessons extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Item> trafficsSigns;
    CustomAdaptor mAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_lessons);
        mRecyclerView = findViewById(R.id.recyclerView);
        trafficsSigns = new ArrayList<>();
        mAdaptor =new CustomAdaptor(this,trafficsSigns);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

         Item sign1 = new Item("Roundabout","Round About Approaching: Indicates that a roundabout is ahead. Slow down when you see this sign. Yeild: The Yield sign indicates to drivers that they must yield the right-of-way, stopping if necessary, before entering the roundabout, and must not proceed until it is safe to do so.",R.mipmap.roundabout);
         Item sign2 = new Item("Traffic Lights","Traffic lights (or traffic signals) are lights used to control the movement of traffic. They are placed at road intersections and crossings. The different colors of lights tell drivers what to do.",R.mipmap.traffic_lights);
         Item sign3 = new Item("One Way","The one way traffic sign is a regulatory sign.Drivers encountering a one way sign must travel in the direction that the sign is pointing. One way signs designate the direction in which traffic is traveling. Drivers must not travel in the opposite direction of the one way sign due to the risk of a head-on collision.",R.mipmap.one_way);
         Item sign4 = new Item("Accident","If you see an accident sign then emergency services will be attending as only a police officer can put this sign up. Other road users can use warning triangles to warn people that there is an accident ahead. ... Protect emergency services workers who are attending the scene.",R.mipmap.accident);
         Item sign5 = new Item("Right Lane Closed Ahead","A Right Lane Closed Ahead Sign is designed to be used well in advance of the lane closure so that drivers are aware of what will happen ahead and begin moving into the left lane(s). This sign is available in multiple size and reflective material options.",R.mipmap.right_lane_closed);
         Item sign6 = new Item("School Zone","A school zone refers to an area on a street near a school or near a crosswalk leading to a school that has a likely presence of younger pedestrians. School zones generally have a reduced speed limit during certain hours",R.mipmap.schzone);
         Item sign7 = new Item("No Right Turn","Do not make a right turn at an intersection marked by the \"NO RIGHT TURN\" sign. You can either travel straight through the intersection or turn ",R.mipmap.no_right_turn);
         Item sign8 = new Item("Road Works Ahead","The highway ahead is undergoing maintenance. Construction and maintenance signs are orange-colored and are used to notify drivers of unusual or potentially dangerous conditions in or near work areas.",R.mipmap.works);
         Item sign9 = new Item("No Overtaking","The no overtaking road sign is circular with a red border meaning that it is giving drivers an order. As the no overtaking sign is regulatory, it is illegal to overtake.",R.mipmap.no_over_taking);
         Item sign10 = new Item("Stop","A stop sign is a regulatory sign - a traffic control device that warns drivers to slow down and prepare to stop. ... Stop signs are the only traffic signs that are octagonal (eight sided). Location: intersections. This regulatory traffic sign is often found at intersections where a road doesn't have traffic lights.",R.mipmap.stop);

         trafficsSigns.add(sign1);
         trafficsSigns.add(sign2);
         trafficsSigns.add(sign3);
         trafficsSigns.add(sign4);
         trafficsSigns.add(sign5);
         trafficsSigns.add(sign6);
         trafficsSigns.add(sign7);
         trafficsSigns.add(sign8);
         trafficsSigns.add(sign9);
         trafficsSigns.add(sign10);

         mRecyclerView.setAdapter(mAdaptor);
    }
}