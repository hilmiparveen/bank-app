import React, { useState } from 'react';

import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom"
import axios from 'axios';

const Address = (props) =>{


    const navigate = useNavigate();
    const {
        register,
        handleSubmit
        } = useForm();
    
    const addressType = props.type;
    const onSubmit = (data)=>{

        let addresses;
        const address = {
            addressType : addressType,
            addressLine1: data.addressLine1,
            addressLine2: data.addressLine2,
            landmark: data.landmark,
            state:data.state,
            city:data.city,
            pincode:data.pincode,
         }
         if(addressType === "Permanent")
            addresses = []
         else{
            addresses = JSON.parse(sessionStorage.getItem("address"))
         }
         addresses.push(address);

        sessionStorage.setItem("address",JSON.stringify(addresses));

        if(addressType === "Permanent")
        navigate('/addTemporaryAddress')



        else
        console.log(JSON.stringify(sessionStorage));
        // navigate('/reviewPage')
    };

    return(

        <div>
            <h1>Creating a {accType} Account</h1>
            <h3>{addressType} Details</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>Address Line 1 </label>
                <input type="text" 
                        name="addressLine1"
                        {...register("addressLine1")}
                        required
                ></input>

                <br></br>

                <label>Address Line 2</label>
                <input type="text" 
                        name="addressLine2"
                        {...register("addressLine2")}
                ></input>

                <br></br>


                <label>Landmark</label>
                <input type="text" 
                        name="landmark"
                        {...register("landmark")}
                ></input>

                <br></br>

                <label>State</label>
                <input type="text" 
                        name="state"
                        {...register("state")}
                ></input>

                <br></br>

                <label>City</label>
                <input type="text" 
                        name="city"
                        {...register("city")}
                ></input>

                <br></br>

                <label>Pincode  </label>
                <input type="number" 
                        name="pincode"
                        {...register("pincode")}
                ></input>

                <br></br>

                <input type="submit" 
                >Next</input>
                
            </form>
        </div>
    )
}

export default Address;