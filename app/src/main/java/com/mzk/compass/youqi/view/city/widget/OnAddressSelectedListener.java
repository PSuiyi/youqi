package com.mzk.compass.youqi.view.city.widget;


import com.mzk.compass.youqi.view.city.bean.City;
import com.mzk.compass.youqi.view.city.bean.County;
import com.mzk.compass.youqi.view.city.bean.Province;
import com.mzk.compass.youqi.view.city.bean.Street;

public interface OnAddressSelectedListener {
    void onAddressSelected(Province province, City city, County county, Street street);
}
