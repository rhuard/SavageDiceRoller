package com.example.rhuard.savagediceroller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;


public class ModifierFragment extends Fragment {

    private int MOD_MAX = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_modifier, container, false);

        //Init radio buttons
        RadioGroup rg = (RadioGroup) view.findViewById(R.id.fragRadioGroup);
        int pos_id = R.id.fragPosButton;
        rg.check(pos_id);

        //Init spinner
        ArrayAdapter<String> values = InitSpinner();
        Spinner ms = (Spinner) view.findViewById(R.id.fragSpinner);
        AdapterView.OnItemSelectedListener listener = new ModifierFragment.ModifierOnItemSelectedListener();
        ms.setAdapter(values);
        ms.setSelection(0);
        ms.setOnItemSelectedListener(listener);

        //Init modifier switch
        Switch mod_switch = (Switch) view.findViewById(R.id.fragmodswitch);
        mod_switch.setOnCheckedChangeListener(new ModifierFragment.SwitchCheckedChangedListener());

        //Init modifier stuff as disabled
        RadioButton pos_button = (RadioButton) view.findViewById(R.id.fragPosButton);
        RadioButton neg_button = (RadioButton) view.findViewById(R.id.fragnegbutton);
        pos_button.setEnabled(false);
        neg_button.setEnabled(false);
        ms.setEnabled(false);

        // Inflate the layout for this fragment
        return view;
    }

    //Listener Classes
    public class SwitchCheckedChangedListener implements CompoundButton.OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            if(isChecked){
                EnableModifier(true);
            }else{
                EnableModifier(false);
            }
        }
    }

    public class ModifierOnItemSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            parent.setSelection(pos);
        }
        public void onNothingSelected(AdapterView<?> parent) {
            //nothing to do
        }
    }

    //Helpers
    public ArrayAdapter<String> InitSpinner(){
        //Init modifier spinner values
        String[] values = new String[MOD_MAX + 1];
        for(int i = 0; i<=MOD_MAX; i++){
            values[i] = String.valueOf(i);
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, values);
        return aa;
    }

    private void EnableModifier(boolean enable){
        RadioButton pos_button = (RadioButton) getView().findViewById(R.id.fragPosButton);
        RadioButton neg_button = (RadioButton) getView().findViewById(R.id.fragnegbutton);
        Spinner ms = (Spinner) getView().findViewById(R.id.fragSpinner);

        if(enable){
            pos_button.setEnabled(true);
            neg_button.setEnabled(true);
            ms.setEnabled(true);
        }else{
            pos_button.setEnabled(false);
            neg_button.setEnabled(false);
            ms.setEnabled(false);
        }
    }
}
