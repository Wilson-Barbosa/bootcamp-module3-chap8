import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../Interfaces/Seller';
import { Observable } from 'rxjs';
import { SelerWithId } from '../Interfaces/SelerWithId';

@Injectable({
    providedIn: 'root'
})
export class SellerService {

    // Injecting the HttpClient as a dependecy
    constructor(private http: HttpClient){}

    // URL to access resources
    baseUrl: string = "http://localhost:8080/sellers";

    // Method that sends an http POST request, registering a new seller in the database
    public save(seller: Seller){
        return this.http.post<Seller>(this.baseUrl, seller);
    }

    //  Method that sends an http GET request and recovers all sellers from the datase
    public getAllSellers(): Observable<SelerWithId[]> {
        return this.http.get<SelerWithId[]>(this.baseUrl);
    }

    // Method that sends an HTTP GET request and recovers a single seller by its id
    public getSellerById(id: number): Observable<SelerWithId> {
        return this.http.get<SelerWithId>(`${this.baseUrl}/${id}`);
    }

    // Method that sends an HTTP DELETE request to remove a seller from the database
    public deleteSellerById(id: number){
        return this.http.delete(`${this.baseUrl}/${id}`);
    }

    // Method that sends an HTTP PUT request to update a seller in the database
    public updateSeller(seller: SelerWithId){
        return this.http.put(`${this.baseUrl}/${seller.id}`, seller); // TODO alter this method later
    }


}
