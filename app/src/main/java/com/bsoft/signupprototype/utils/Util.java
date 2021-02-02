package com.bsoft.signupprototype.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bsoft.signupprototype.item.Credential;
import com.bsoft.signupprototype.item.Detail;

import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static final short ToastDuration = Toast.LENGTH_LONG;
    public static final String HomeUrl = "https://defoody.com/";

    private static final char[] alphas = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static final String[] States = {
            "Abia", "Adamawa", "Akwa Ibom", "Anambra", "Bauchi", "Bayelsa", "Benue",
            "Borno", "Cross River", "Delta", "Ebonyi", "Edo", "Ekiti", "Enugu", "FCT",
            "Gombe", "Imo", "Jigawa", "Kaduna", "Kano", "Katsina", "Kebbi", "Kogi",
            "Kwara", "Lagos", "Nasarawa", "Niger", "Ogun", "Ondo", "Osun", "Oyo",
            "Plateau", "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara"};

    public static String[] GetLGA(String state){
        String[][] lgas = {
                {"Aba North", "Aba South", "Arochukwu", "Bende", "Ikwuano", "Isiala Ngwa North", "Isiala Ngwa South", "Isuikwuato", "Obi Ngwa", "Ohafia", "Osisioma", "Ugwunagbo", "Ukwa East", "Ukwa West", "Umuahia North", "Umuahia South", "Umu Nneochi", "Obi Ngwa"},
                {"Demsa", "Fufure", "Ganye", "Gayuk", "Gombi", "Grie", "Hong", "Jada", "Lamurde", "Madagali", "Maiha", "Mayo Belwa", "Michika", "Mubi North", "Mubi South", "Numan", "Shelleng", "Song", "Toungo", "Yola North", "Yola South"},
                {"Abak", "Eastern Obolo", "Eket", "Esit Eket", "Essien Udim", "Etim Ekpo", "Etinan", "Ibeno", "Ibesikpo Asutan", "Akwa-Ibom", "Ika", "Ikono", "Ikot Abasi", "Ikot Ekpene", "Ini", "Itu", "Mbo", "Mkpat-Enin", "Nsit-Atai", "Nsit-Ibom", "Nsit-Ubium", "Obot Akara", "Okobo", "Onna", "Oron", "Oruk Anam", "Udung-Uko", "Ukanafun", "Uruan", "Urue-Offong/Oruko", "Uyo", "Obot Akara"},
                {"Aguata", "Anambra East", "Anambra West", "Anaocha", "Awka North", "Awka South", "Ayamelum", "Dunukofia", "Ekwusigo", "Idemili North", "Idemili South", "Ihiala", "Njikoka", "Nnewi North", "Nnewi South", "Ogbaru", "Onitsha North", "Onitsha South", "Orumba North", "Orumba South", "Oyi"},
                {"Alkaleri", "Bauchi", "Bogoro", "Damban", "Darazo", "Dass", "Gamawa", "Ganjuwa", "Giade", "Itas/Gadau", "Jama'are", "Katagum", "Kirfi", "Misau", "Ningi", "Shira", "Tafawa Balewa", "Toro", "Warji", "Zaki"},
                {"Brass", "Ekeremor", "Kolokuma/Opokuma", "Nembe", "Ogbia", "Sagbama", "Southern Ijaw", "Yenagoa"},
                {"Agatu", "Apa", "Ado", "Buruku", "Gboko", "Guma", "Gwer East", "Gwer West", "Katsina-Ala", "Konshisha", "Kwande", "Logo", "Makurdi", "Obi", "Ogbadibo", "Ohimini", "Oju", "Okpokwu", "Oturkpo", "Tarka", "Ukum", "Ushongo", "Vandeikya", "Obi"},
                {"Abadam", "Askira/Uba", "Bama", "Bayo", "Biu", "Chibok", "Damboa", "Dikwa", "Gubio", "Guzamala", "Gwoza", "Hawul", "Jere", "Kaga", "Kala/Balge", "Konduga", "Kukawa", "Kwaya Kusar", "Mafa", "Magumeri", "Maiduguri", "Marte", "Mobbar", "Monguno", "Ngala", "Nganzai", "Shani"},
                {"Abi", "Akamkpa", "Akpabuyo", "Bakassi", "Bekwarra", "Biase", "Boki", "Calabar Municipal", "Calabar South", "Etung", "Ikom", "Obanliku", "Obubra", "Obudu", "Odukpani", "Ogoja", "Yakuur", "Obubra", "Yala"},
                {"Aniocha North", "Aniocha South", "Bomadi", "Burutu", "Ethiope East", "Ethiope West", "Ika North East", "Ika South", "Isoko North", "Isoko South", "Ndokwa East", "Ndokwa West", "Okpe", "Oshimili North", "Oshimili South", "Patani", "Sapele", "Udu", "Ughelli North", "Ughelli South", "Ukwuani", "Uvwie", "Warri North", "Warri South", "Warri South West"},
                {"Abakaliki", "Afikpo North", "Afikpo South (Edda)", "Ebonyi", "Ezza North", "Ezza South", "Ikwo", "Ishielu", "Ivo", "Izzi", "Ohaozara", "Ohaukwu", "Onicha"},
                {"Akoko-Edo", "Egor", "Esan Central", "Esan North-East", "Esan South-East", "Esan West", "Etsako Central", "Etsako East", "Etsako West", "Igueben", "Ikpoba Okha", "Orhionmwon", "Oredo", "Ovia North-East", "Ovia South-West", "Owan East", "Owan West", "Uhunmwonde"},
                {"Ado Ekiti", "Efon", "Ekiti East", "Ekiti South-West", "Ekiti West", "Emure", "Gbonyin", "Ido Osi", "Ijero", "Ikere", "Ikole", "Ilejemeje", "Irepodun/Ifelodun", "Ise/Orun", "Moba", "Oye"},
                {"Aninri", "Awgu", "Enugu East", "Enugu North", "Enugu South", "Ezeagu", "Igbo Etiti", "Igbo Eze North", "Igbo Eze South", "Isi Uzo", "Nkanu East", "Nkanu West", "Nsukka", "Oji River", "Udenu", "Udi", "Uzo-Uwani"},
                {"Abaji", "Bwari", "Gwagwalada", "Kuje", "Kwali", "Municipal Area Council"},
                {"Akko", "Balanga", "Billiri", "Dukku", "Funakaye", "Gombe", "Kaltungo", "Kwami", "Nafada", "Shongom", "Yamaltu/Deba"},
                {"Aboh Mbaise", "Ahiazu Mbaise", "Ehime Mbano", "Ezinihitte", "Ideato North", "Ideato South", "Ihitte/Uboma", "Ikeduru", "Isiala Mbano", "Isu", "Mbaitoli", "Ngor},Okpala", "Njaba", "Nkwerre", "Nwangele", "Obowo", "Oguta", "Ohaji/Egbema", "Okigwe", "Orlu", "Orsu", "Oru East", "Oru West", "Owerri Municipal", "Owerri North", "Owerri West", "Unuimo", "Obowo"},
                {"Auyo", "Babura", "Biriniwa", "Birnin Kudu", "Buji", "Dutse", "Gagarawa", "Garki", "Gumel", "Guri", "Gwaram", "Gwiwa", "Hadejia", "Jahun", "Kafin Hausa", "Kazaure", "Kiri Kasama", "Kiyawa", "Kaugama", "Maigatari", "Malam Madori", "Miga", "Ringim", "Roni", "Sule Tankarkar", "Taura", "Yankwashi"},
                {"Birnin Gwari", "Chikun", "Giwa", "Igabi", "Ikara", "Jaba", "Jema'a", "Kachia", "Kaduna North", "Kaduna South", "Kagarko", "Kajuru", "Kaura", "Kauru", "Kubau", "Kudan", "Lere", "Makarfi", "Sabon Gari", "Sanga", "Soba", "Zangon Kataf", "Zaria"},
                {"Ajingi", "Albasu", "Bagwai", "Bebeji", "Bichi", "Bunkure", "Dala", "Dambatta", "Dawakin Kudu", "Dawakin Tofa", "Doguwa", "Fagge", "Gabasawa", "Garko", "Garun Mallam", "Gaya", "Gezawa", "Gwale", "Gwarzo", "Kabo", "Kano Municipal", "Karaye", "Kibiya", "Kiru", "Kumbotso", "Kunchi", "Kura", "Madobi", "Makoda", "Minjibir", "Nasarawa", "Rano", "Rimin Gado", "Rogo", "Shanono", "Sumaila", "Takai", "Tarauni", "Tofa", "Tsanyawa", "Tudun Wada", "Ungogo", "Warawa", "Wudil"},
                {"Bakori", "Batagarawa", "Batsari", "Baure", "Bindawa", "Charanchi", "Dandume", "Danja", "Dan Musa", "Daura", "Dutsi", "Dutsin Ma", "Faskari", "Funtua", "Ingawa", "Jibia", "Kafur", "Kaita", "Kankara", "Kankia", "Katsina", "Kurfi", "Kusada", "Mai'Adua", "Malumfashi", "Mani", "Mashi", "Matazu", "Musawa", "Rimi", "Sabuwa", "Safana", "Sandamu", "Zango"},
                {"Aleiro", "Arewa Dandi", "Argungu", "Augie", "Bagudo", "Birnin Kebbi", "Bunza", "Dandi", "Fakai", "Gwandu", "Jega", "Kalgo", "Koko/Besse", "Maiyama", "Ngaski", "Sakaba", "Shanga", "Suru", "Wasagu/Danko", "Yauri", "Zuru"},
                {"Adavi", "Ajaokuta", "Ankpa", "Bassa", "Dekina", "Ibaji", "Idah", "Igalamela Odolu", "Ijumu", "Kabba/Bunu", "Kogi", "Lokoja", "Mopa Muro", "Ofu", "Ogori/Magongo", "Okehi", "Okene", "Olamaboro", "Omala", "Yagba East", "Yagba West"},
                {"Asa", "Baruten", "Edu", "Ekiti", "Ifelodun", "Ilorin East", "Ilorin South", "Ilorin West", "Irepodun", "Isin", "Kaiama", "Moro", "Offa", "Oke Ero", "Oyun", "Pategi"},
                {"Agege", "Ajeromi-Ifelodun", "Alimosho", "Amuwo-Odofin", "Apapa", "Badagry", "Epe", "Eti Osa", "Ibeju-Lekki", "Ifako-Ijaiye", "Ikeja", "Ikorodu", "Kosofe", "Lagos Island", "Lagos Mainland", "Mushin", "Ojo", "Oshodi-Isolo", "Shomolu", "Surulere"},
                {"Akwanga", "Awe", "Doma", "Karu", "Keana", "Keffi", "Kokona", "Lafia", "Nasarawa", "Nasarawa Egon", "Obi", "Toto", "Wamba", "Obi"},
                {"Agaie", "Agwara", "Bida", "Borgu", "Bosso", "Chanchaga", "Edati", "Gbako", "Gurara", "Katcha", "Kontagora", "Lapai", "Lavun", "Magama", "Mariga", "Mashegu", "Mokwa", "Moya", "Paikoro", "Rafi", "Rijau", "Shiroro", "Suleja", "Tafa", "Wushishi"},
                {"Abeokuta North", "Abeokuta South", "Ado-Odo/Ota", "Yewa North", "Yewa South", "Ewekoro", "Ifo", "Ijebu East", "Ijebu North", "Ijebu North East", "Ijebu Ode", "Ikenne", "Imeko Afon", "Ipokia", "Obafemi Owode", "Odeda", "Odogbolu", "Ogun Waterside", "Remo North", "Shagamu"},
                {"Akoko North-East", "Akoko North-West", "Akoko South-West", "Akoko South-East", "Akure North", "Akure South", "Ese Odo", "Idanre", "Ifedore", "Ilaje", "Ile Oluji/Okeigbo", "Irele", "Odigbo", "Okitipupa", "Ondo East", "Ondo West", "Ose", "Owo"},
                {"Atakunmosa East", "Atakunmosa West", "Aiyedaade", "Aiyedire", "Boluwaduro", "Boripe", "Ede North", "Ede South", "Ife Central", "Ife East", "Ife North", "Ife South", "Egbedore", "Ejigbo", "Ifedayo", "Ifelodun", "Ila", "Ilesa East", "Ilesa West", "Irepodun", "Irewole", "Isokan", "Iwo", "Obokun", "Odo Otin", "Ola Oluwa", "Olorunda", "Oriade", "Orolu", "Osogbo", "Obokun"},
                {"Afijio", "Akinyele", "Atiba", "Atisbo", "Egbeda", "Ibadan North", "Ibadan North-East", "Ibadan North-West", "Ibadan South-East", "Ibadan South-West", "Ibarapa Central", "Ibarapa East", "Ibarapa North", "Ido", "Irepo", "Iseyin", "Itesiwaju", "Iwajowa", "Kajola", "Lagelu", "Ogbomosho North", "Ogbomosho South", "Ogo Oluwa", "Olorunsogo", "Oluyole", "Ona Ara", "Orelope", "Ori Ire", "Oyo West", "Oyo East", "Saki East", "Saki West", "Surulere"},
                {"Bokkos", "Barkin Ladi", "Bassa", "Jos East", "Jos North", "Jos South", "Kanam", "Kanke", "Langtang South", "Langtang North", "Mangu", "Mikang", "Pankshin", "Qua'an Pan", "Riyom", "Shendam", "Wase"},
                {"Abua/Odual", "Ahoada East", "Ahoada West", "Akuku-Toru", "Andoni", "Asari-Toru", "Bonny", "Degema", "Eleme", "Emuoha", "Etche", "Gokana", "Ikwerre", "Khana", "Obio/Akpor", "Ogba/Egbema/Ndoni", "Ogu/Bolo", "Okrika", "Omuma", "Opobo/Nkoro", "Oyigbo", "Port Harcourt", "Tai", "Obio/Akpor"},
                {"Binji", "Bodinga", "Dange Shuni", "Gada", "Goronyo", "Gudu", "Gwadabawa", "Illela", "Isa", "Kebbe", "Kware", "Rabah", "Sabon Birni", "Shagari", "Silame", "Sokoto North", "Sokoto South", "Tambuwal", "Tangaza", "Tureta", "Wamako", "Wurno", "Yabo"},
                {"Ardo Kola", "Bali", "Donga", "Gashaka", "Gassol", "Ibi", "Jalingo", "Karim Lamido", "Kumi", "Lau", "Sardauna", "Takum", "Ussa", "Wukari", "Yorro", "Zing"},
                {"Bade", "Bursari", "Damaturu", "Fika", "Fune", "Geidam", "Gujba", "Gulani", "Jakusko", "Karasuwa", "Machina", "Nangere", "Nguru", "Potiskum", "Tarmuwa", "Yunusari", "Yusufari"},
                {"Anka", "Bakura", "Birnin Magaji/Kiyaw", "Bukkuyum", "Bungudu", "Gummi", "Gusau", "Kaura Namoda", "Maradun", "Maru", "Shinkafi", "Talata Mafara", "Chafe", "Zurmi"}};

        if(!state.isEmpty()){
            for (int i = 0; i < States.length; i++){
                if(States[i].toLowerCase().equals(state.toLowerCase())){
                    return lgas[i];
                }
            }
        }
        return null;
    }

    public static Typeface FontAwsome (Context context){
        return Typeface.createFromAsset(context.getAssets(),"fa-solid-900.ttf");
    }

    public static Typeface RegularFontAwsome (Context context){
        return Typeface.createFromAsset(context.getAssets(),"fa-regular-400.ttf");
    }

    public static Typeface BrandsFontAwsome (Context context){
        return Typeface.createFromAsset(context.getAssets(),"fa-brands-400.ttf");
    }

    public static void setFontAwsome(Context context,View... views){
        for(View view : views){
            try {
                if (view instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) view;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        Util.setFontAwsome(context, child);
                    }
                } else if (view instanceof TextView) {
                    ((TextView)view).setTypeface(FontAwsome(context), Typeface.BOLD);
                }
            } catch (Exception e) {
                Log.e("Font error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

    public static void setFontAwsomeWidthId(Context context,View view, int... ids){
        for(int id : ids){
            View init = view.findViewById(id);
            try {
                if (init instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) init;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        Util.setFontAwsome(context, child);
                    }
                } else if (init instanceof TextView) {
                    ((TextView) init).setTypeface(FontAwsome(context), Typeface.BOLD);
                }
            } catch (Exception e) {
                Log.e("Font error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

    public static void setRegularFontAwsome(Context context,View... views){
        for(View view : views){
            try {
                if (view instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) view;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        Util.setRegularFontAwsome(context, child);
                    }
                } else if (view instanceof TextView) {
                    ((TextView)view).setTypeface(RegularFontAwsome(context), Typeface.BOLD);
                }
            } catch (Exception e) {
                Log.e("Font error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

    public static void setRegularFontAwsomeWidthId(Context context,View view, int... ids){
        for(int id : ids){
            View init = view.findViewById(id);
            try {
                if (init instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) init;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        Util.setRegularFontAwsome(context, child);
                    }
                } else if (init instanceof TextView) {
                    ((TextView) init).setTypeface(RegularFontAwsome(context), Typeface.BOLD);
                }
            } catch (Exception e) {
                Log.e("Font error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

    public static void setBrandFontAwsome(Context context,View... views){
        for(View view : views){
            try {
                if (view instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) view;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        Util.setBrandFontAwsome(context, child);
                    }
                } else if (view instanceof TextView) {
                    ((TextView)view).setTypeface(BrandsFontAwsome(context), Typeface.BOLD);
                }
            } catch (Exception e) {
                Log.e("Font error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

    public static void setBrandFontAwsomeWidthId(Context context,View view, int... ids){
        for(int id : ids){
            View init = view.findViewById(id);
            try {
                if (init instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) init;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        Util.setBrandFontAwsome(context, child);
                    }
                } else if (init instanceof TextView) {
                    ((TextView) init).setTypeface(BrandsFontAwsome(context), Typeface.BOLD);
                }
            } catch (Exception e) {
                Log.e("Font error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

    public static void setFont(final Context context, final View v, String fontPath) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    Util.setFont(context, child, fontPath);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), fontPath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean saveCredential(Context context, Credential credential){
        try {
            ObjectIO objectIO = new ObjectIO(context, "data");
            objectIO.writeObjectToFile(new String[]{"credential"}, credential);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Credential retrieveCredential(Context context){
        ObjectIO objectIO = new ObjectIO(context, "data");
        Object[] savedObjects = objectIO.readObjectFromFile("credential");
        if (savedObjects != null && savedObjects.length > 0) {
            return ((Credential) savedObjects[0]);

        }
        return null;
    }

    public static String generateUid(String username, String state){
        StringBuilder init = new StringBuilder();
        for(char c : username.toCharArray()){
            if(Character.isLowerCase(c)){
                init.append(Character.toUpperCase(c));
            }else if(Character.isDigit(c)){
                init.append(alphas[(int)c]);
            }
        }

        if(init.toString().length() <= 12) {
            while (init.toString().length() < 12) {
                init.append(alphas[new Random().nextInt(alphas.length)]);
            }
        }
        return state.substring(0, 2).toUpperCase() + rearrange(init.toString());
    }

    private static String rearrange(String input){
        StringBuilder builder = new StringBuilder();
        while (input.length() > 0){
            int index = new Random().nextInt(input.length());
            builder.append(input.charAt(index));
            StringBuilder init = new StringBuilder();
            for (int i = 0; i < input.length(); i++){
                if(i != index){ init.append(input.charAt(i)); }
            }
            input = init.toString();
        }
        return builder.toString();
    }

    public static boolean isFormValid(Context context, View... views){
        for (View view : views){
            if(view instanceof EditText || view instanceof AutoCompleteTextView){
                if(((EditText)view).getText().toString().isEmpty()){
                    Toast.makeText(context, view.getTag().toString() + " cannot be left empty", ToastDuration).show();
                    return  false;
                }
            }else if(view instanceof Spinner){
                if(((Spinner)view).getSelectedItemPosition() <= 0){
                    Toast.makeText(context, "An option must be selected in " + view.getTag().toString(), ToastDuration).show();
                    return false;
                }
            }
        }
        return true;
    }

    public static void loadFragment(AppCompatActivity activity, Fragment fragment, int layout){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, fragment);
        transaction.commit();
    }

    public static boolean isEmail(String input){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(input);
        return mat.matches();
    }
}
