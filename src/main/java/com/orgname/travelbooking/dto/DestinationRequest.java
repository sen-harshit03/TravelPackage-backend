package com.orgname.travelbooking.dto;

import com.orgname.travelbooking.entities.Activity;


import java.util.List;
import java.util.Set;

public record DestinationRequest (String name, Set<Activity> activities) {

}
