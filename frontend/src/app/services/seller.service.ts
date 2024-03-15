import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../Interfaces/Seller';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SellerService {

    constructor(private http: HttpClient){}

    // URL to access resources
    baseUrl: string = "http://localhost:8080/sellers";

    // Method that sends an http POST request, registering a new seller in the database
    public save(seller: Seller){
        return this.http.post<Seller>(this.baseUrl, seller);
    }

    //  Method that sends an http GET request and recovers all sellers from the datase
    public getSellers(): Observable<Seller[]> {
        return this.http.get<Seller[]>(this.baseUrl);
    }

    // Method that sends an HTTP GET request and recovers a single seller by its id
    public getSeller(id: number): Observable<Seller> {
        return this.http.get<Seller>(`${this.baseUrl}/${id}`);
    }

}
