import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Seller } from 'src/app/Interfaces/Seller';
import { SellerService } from 'src/app/services/seller.service';

@Component({
    selector: 'app-register-seller',
    templateUrl: './register-seller.component.html',
    styleUrls: ['./register-seller.component.css']
})
export class RegisterSellerComponent {

    constructor(private sellerService: SellerService){}

    // Initializing an Empty seller Object
    seller: Seller = {} as Seller;

    // FormGroup Object containing a list of FormControls
    sellerForm = new FormGroup({
        name: new FormControl(''),
        salary: new FormControl(''),
        bonus: new FormControl(''),
        gender: new FormControl('')
    });


    // Methods that gets called when trying to save a seller
    public saveSeller(){

        // Assigns the values from the form to the Seller attribute
        Object.assign(this.seller, this.sellerForm.value);

        // The Calls the POST method from the service
        this.sellerService.save(this.seller).subscribe(
            {
                next: () => {alert("The seller was registered!")}
            }
        );

    }

    


}
