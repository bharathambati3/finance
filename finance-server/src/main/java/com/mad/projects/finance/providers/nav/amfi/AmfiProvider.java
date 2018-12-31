package com.mad.projects.finance.providers.nav.amfi;

import com.mad.projects.finance.db.entity.MFScheme;
import com.mad.projects.finance.providers.nav.MFNavProvider;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AmfiProvider implements MFNavProvider {

    @Override
    public Map<MFScheme, BigDecimal> getLatestNav(List<MFScheme> schemes) {
        BufferedReader br = null;
        Map<MFScheme, BigDecimal> navMap = new HashMap<>();
        try {

            URL url = new URL("https://www.amfiindia.com/spages/NAVAll.txt");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            br = new BufferedReader(new InputStreamReader(inputStream));


            String line;
            while((line = br.readLine()) != null) {

                if (navMap.size() == schemes.size()) {
                    break;
                }



                if(line.length() > 6) {
                    String id = line.substring(0, 6);
                    Optional<MFScheme> optionalScheme = schemes.stream()
                            .filter(s -> s.getAmfiCode().equalsIgnoreCase(id))
                            .findFirst();

                    if(optionalScheme.isPresent()) {

                        //line format.
                        //Scheme Code;ISIN Div Payout/ ISIN Growth;ISIN Div Reinvestment;Scheme Name;Net Asset Value;Repurchase Price;Sale Price;Date
                        String[] split = line.split(";");
                        String nav = split[4];
                        navMap.put(optionalScheme.get(), new BigDecimal(nav));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); //todo:: log
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();  //todo:: log
                }
            }
        }
        return navMap;
    }
}
