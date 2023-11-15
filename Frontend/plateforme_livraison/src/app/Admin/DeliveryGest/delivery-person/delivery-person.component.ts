import { Component } from '@angular/core';
import { User } from 'src/app/Model/User';
import { ServiceLivreurService } from 'src/app/Service/service-livreur.service';
import { MatDialog } from '@angular/material/dialog';
import { NewDeliveryPersonComponent } from '../new-delivery-person/new-delivery-person.component';

@Component({
  selector: 'app-delivery-person',
  templateUrl: './delivery-person.component.html',
  styleUrls: ['./delivery-person.component.css']
})
export class DeliveryPersonComponent {
  search!:string;

  user: User[] = []; // Initialize users as an empty array
    constructor(private LivreurServ:ServiceLivreurService , private dialog: MatDialog){
    }

    ngOnInit(){
      this.getAll()
    }
    getAll(){
      this.LivreurServ.getAllLivreurs().subscribe((res:any)=>{
        this.user=res;
        console.log(this.user);

      },
        (      error: any)=>{console.log(error)})
    }


    blockLivreur(livreurId: number,nom:string) {
      if(confirm("Are you sure to block "+nom)) {

      this.LivreurServ.blockLivreur(livreurId).subscribe(
        (response) => {
          console.log("Livreur bloqué avec succès", response);
          this.getAll(); 
        },
        (error) => {
          console.error("Erreur lors du blocage du livreur", error);
        }
      );
    }
  }

  
    deblockLivreur(livreurId: number,nom:string) {
      if(confirm("Are you sure to deblock "+nom)) {

      this.LivreurServ.deblockLivreur(livreurId).subscribe(
        (response) => {
          console.log("Livreur débloqué avec succès", response);
          this.getAll(); 
        },
        (error) => {
          console.error("Erreur lors du déblocage du livreur", error);
        }
      );
    }
  }

    deleteLivreur(livreurId: number,nom:string) {
      if(confirm("Are you sure to delete "+nom)) {
      this.LivreurServ.deleteLivreur(livreurId).subscribe(
        () => {
          console.log("Livreur deleted successfully");
          this.getAll(); // Par exemple, rafraîchissez la liste des utilisateurs.
        },
        (error) => {
          console.error("Erreur lors de la suppression du livreur", error);
        }
      );
    }
  }
    


    openModal() {
      const dialogRef = this.dialog.open(NewDeliveryPersonComponent, {});
    
      dialogRef.afterClosed().subscribe((result) => {
        
        console.log('Modal closed with result: ', result);
      });
    
      dialogRef.componentInstance.deliveryPersonCreated.subscribe(() => {
        dialogRef.close(); 
        this.getAll();

      });
    }
    

}
