package com.sumit.hotel.service.repositories;

import com.sumit.hotel.service.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String > {
}
