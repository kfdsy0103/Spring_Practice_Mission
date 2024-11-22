package javalab.umc7th_mission.converter;

import javalab.umc7th_mission.domain.FoodCategory;
import javalab.umc7th_mission.domain.mapping.MemberFood;

import java.util.List;

public class MemberFoodConverter {

    public static List<MemberFood> toMemberPreferList(List<FoodCategory> foodCategoryList){
        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberFood.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).toList();
    }
}
