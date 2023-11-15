import { Component } from '@angular/core';
import { Partenaire } from 'src/app/Partenaire/Partenaire';
import { PartenaireService } from 'src/app/Service/partenaire.service';

@Component({
  selector: 'app-partenaire',
  templateUrl: './partenaire.component.html',
  styleUrls: ['./partenaire.component.css']
})
export class PartenaireComponent {

  user: Partenaire[] = [];
  constructor(private PartServ:PartenaireService){}
  ngOnInit(){
    this.getAll();
  }
  getAll(){
    return this.PartServ.getAllPartenaire().subscribe((res:any)=>{
      this.user=res;
      console.log(this.user);

    },(error:any)=>(console.log(error)))
  }

  deletePartner(id: number,nom:string) {
    if(confirm("Are you sure to delete "+nom)) {
    this.PartServ.deletePartner(id).subscribe(
      () => {
        console.log("Partner deleted successfully");
        this.getAll(); // Par exemple, rafraîchissez la liste des utilisateurs.
      },
      (error) => {
        console.error("Erreur lors de la suppression du Partner", error);
      }
    );
  }
}

blockPartenaire(id: number,nom:string) {
  if(confirm("Are you sure to block "+nom)) {

  this.PartServ.blockPartenaire(id).subscribe(
    (response) => {
      console.log("Partenaire bloqué avec succès", response);
      this.getAll(); 
    },
    (error) => {
      console.error("Erreur lors du blocage du Partenaire", error);
    }
  );
}
}


deblockPartenaire(id: number,nom:string) {
  if(confirm("Are you sure to deblock "+nom)) {

  this.PartServ.deblockPartenaire(id).subscribe(
    (response) => {
      console.log("Partenaire debloqué avec succès", response);
      this.getAll(); 
    },
    (error) => {
      console.error("Erreur lors du deblocage du Partenaire", error);
    }
  );
}
}
}
