import { Component, OnInit } from '@angular/core';
import { Seller } from 'src/app/Interfaces/Seller';
import { SellerService } from 'src/app/services/seller.service';

@Component({
    selector: 'app-sellers-list',
    templateUrl: './sellers-list.component.html',
    styleUrls: ['./sellers-list.component.css']
})
export class SellersListComponent {


    sellerList: Seller[] = [];      // Array that stores the list os sellers to be displayed
    showResults: boolean = false;   // Attribute that displays or hides the resultsContainer
    idSellerToSearch!: number;      // Attribute that holds a seller's ID to be searched

    // Injecting the HTTP service via constructor
    constructor(private sellerService: SellerService) { }

    // Method that calls the service method to display all sellers
    public displaySellers(): void {
        this.sellerService.getSellers().subscribe(
            {
                // If the request is successful then the response is assign to SellersArray
                next: sellers => { this.sellerList = sellers }
            }
        );

        // Display the results for the user
        this.showResults = true;
    }

    // Method that calls the service method to display a single seller by its ID
    public displaySingleSeller(): void {

        // This clears the SellerArray before searching for a new one
        // so thing don't bug out
        this.sellerList = [];

        this.sellerService.getSeller(this.idSellerToSearch).subscribe(
            {
                // If the request is successful it gets pushed to SellerArray
                next: seller => { this.sellerList.push(seller) }
            }
        );

        // Display the results for the user
        this.showResults = true;

    }

}
