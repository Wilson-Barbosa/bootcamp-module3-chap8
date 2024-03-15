import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../Interfaces/Seller';

@Injectable({
    providedIn: 'root'
})
export class SellerService {

    constructor(private http: HttpClient){}

    // URL to access resources
    baseUrl: string = "http://localhost:8080/sellers";

    // Method that sends a http POST request, registering a new seller in the database
    public save(seller: Seller){
        return this.http.post<Seller>(this.baseUrl, seller);
    }

}
