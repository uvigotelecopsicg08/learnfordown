    package com.uvigo.learnfordown.learnfordown;

    import android.content.Context;
    import android.graphics.Color;
    import android.net.Uri;
    import android.support.v4.content.ContextCompat;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.util.List;

    import static android.R.attr.label;

    /**
     * Created by zadak on 05/02/2017.
     */

    public class SpinnerAdapter extends ArrayAdapter<SpinnerModel> {
        private int customView;
        String defecto;
        private LayoutInflater layoutInflater;

        public SpinnerAdapter(Context context, int resource, List<SpinnerModel> objects,String Prompt) {
            super(context, resource, objects);
            defecto = Prompt;
            this.customView = resource;
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /**
         * Should override this method if you need to use a custom view.
         */
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);

        }

        /**
         * Should override this method if you need to use a custom view.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
        public View getCustomView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = layoutInflater.inflate(customView, parent, false);
            }

            TextView nameTxtView = (TextView) convertView.findViewById(R.id.nameTextView);
            ImageView valueTxtView = (ImageView) convertView.findViewById(R.id.valueTextView);
            SpinnerModel nameValue = getItem(position);
            nameTxtView.setText(nameValue.getName());
            valueTxtView.setImageURI(Uri.parse(nameValue.getValue()));

            nameTxtView.setVisibility(View.VISIBLE);
            switch (position){
                case 0:
                    nameTxtView.setVisibility(View.GONE);
                    break;
                case 1:
                    nameTxtView.setTextColor(ContextCompat.getColor(getContext(), R.color.Naranja));
                    valueTxtView.setImageResource(R.mipmap.ic_addorange);


                    break;


                case 2:
                    nameTxtView.setTextColor(ContextCompat.getColor(getContext(), R.color.Verde));
                    valueTxtView.setImageResource(R.mipmap.ic_addgreen);

                    break;



                case 3:
                    nameTxtView.setTextColor(ContextCompat.getColor(getContext(), R.color.Rojo));
                    valueTxtView.setImageResource(R.mipmap.ic_addred);

                    break;


                case 4:
                    nameTxtView.setTextColor(ContextCompat.getColor(getContext(), R.color.Morado));
                    break;





            }
            if (defecto.equals("LETRA")){
        valueTxtView.setVisibility(View.GONE);
    }

                return convertView;

        }
    }
