import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SelerWithId } from 'src/app/Interfaces/SelerWithId';
import { SellerService } from 'src/app/services/seller.service';

@Component({
    selector: 'app-sellers-list',
    templateUrl: './sellers-list.component.html',
    styleUrls: ['./sellers-list.component.css']
})
export class SellersListComponent {

    updatedSeller: SelerWithId = {} as SelerWithId;  // Attribute that holds data of a single seller

    sellerList: SelerWithId[] = [];  // Array that stores the list os sellers to be displayed
    showResults: boolean = false;    // Attribute that displays or hides the resultsContainer
    idSellerToSearch!: number;       // Attribute that holds a seller's ID to be searched

    sellerId!: number;  // Seller variable that is displayed on the modal

    // Injecting the HTTP service via constructor
    constructor(private sellerService: SellerService, private modalService: NgbModal) { }


    // Method that calls the service to display all sellers
    public displaySellers(): void {
        this.sellerService.getAllSellers().subscribe(
            {
                // If the request is successful then the response is assign to SellersArray
                next: (sellers) => {this.sellerList = sellers;}
            }
        );

        // Display the results for the user
        this.showResults = true;
    }


    // Method that calls the service to display a single seller by its ID
    public displaySingleSeller(): void {

        // This clears the SellerArray before searching for a new one
        // so things don't bug out
        this.sellerList = [];

        this.sellerService.getSellerById(this.idSellerToSearch).subscribe(
            {
                // If the request is successful it gets pushed to SellerArray
                next: seller => {this.sellerList.push(seller);}
            }
        );

        // Display the results for the user
        this.showResults = true;
    }


    // Method that calls the service to delete a single seller by its ID
    public deleteSeller(modal: any, id: number): void {

        this.sellerId = id;

        //Add a modal to ask if the user wants to delete it
        this.modalService.open(modal).result.then(
            (confirm) => {
                if (confirm) {
                    this.sellerService.deleteSellerById(id).subscribe({
                        next: () => {
                            this.displaySellers(); // reloads the list if the delete request is successfull
                        }
                    });
                }
            }
        );
    }


    // Method that sends a seller to be updated
    public sendSellerToUpdate(seller: SelerWithId): void {
        this.updatedSeller = seller;
    }


    // Method that "clears" the updatedSeller attribute via eventemmiter from the child
    public resetUpdatedSeller(): void {
        this.updatedSeller = {} as SelerWithId;
    }

}
