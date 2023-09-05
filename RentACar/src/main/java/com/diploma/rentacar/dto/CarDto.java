package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.enums.CarTypeEnum;
import com.diploma.rentacar.enums.FuelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;

    private Double price;

    private int numberOfPassengers;

    private int numberOfBags;

    private int minimumAgeWithoutFee;

    private String name;

    private String automaker;

    private String photoPath;

    private boolean automatic;

    private boolean discountApplicable;

    private CarTypeEnum carType;

    private FuelTypeEnum fuelType;

    private String carDealer;

    private List<ReviewDto> reviews;

    public static CarDto fromEntity(Car car) {
        CarDto carDto = CarDto.builder()
                .id(car.getId())
                .price(car.getPrice())
                .carDealer(car.getCarDealer() != null ? car.getCarDealer().getName() : null)
                .numberOfBags(car.getNumberOfBags())
                .numberOfPassengers(car.getNumberOfPassengers())
                .minimumAgeWithoutFee(car.getMinimumAgeWithoutFee())
                .name(car.getName())
                .automaker(car.getAutomaker())
                .photoPath(car.getPhotoPath())
                .automatic(car.isAutomatic())
                .discountApplicable(car.isDiscountApplicable())
                .carType(car.getCarType())
                .fuelType(car.getFuelType())
                .build();

        if(CollectionUtils.isEmpty(car.getReviews())){
            carDto.setReviews(car.getReviews().stream().map(review -> ReviewDto.fromEntity(review)).collect(Collectors.toList()));
        }

        return carDto;

    }

    public static Car toEntity(CarDto car) {
        return Car.builder()
                .id(car.getId())
                .price(car.getPrice())
                .numberOfBags(car.getNumberOfBags())
                .numberOfPassengers(car.getNumberOfPassengers())
                .minimumAgeWithoutFee(car.getMinimumAgeWithoutFee())
                .name(car.getName())
                .automaker(car.getAutomaker())
                .photoPath(car.getPhotoPath())
                .automatic(car.isAutomatic())
                .discountApplicable(car.isDiscountApplicable())
                .carType(car.getCarType())
                .fuelType(car.getFuelType())
                .build();
    }
}
