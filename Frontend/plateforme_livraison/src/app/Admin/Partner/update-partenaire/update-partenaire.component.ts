import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Partenaire } from 'src/app/Partenaire/Partenaire';
import { PartenaireService } from 'src/app/Service/partenaire.service';

@Component({
  selector: 'app-update-partenaire',
  templateUrl: './update-partenaire.component.html',
  styleUrls: ['./update-partenaire.component.css']
})
export class UpdatePartenaireComponent {
  id!: number;
  PartnerData:Partenaire=new Partenaire();
  UpdatePartenaire:Partenaire=new Partenaire();

  constructor(
    private route: ActivatedRoute,
    private PartServ:PartenaireService,
    private router: Router
  ){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id']; 

      this.loadPartnerData();
    });
  }
  loadPartnerData() {
    this.PartServ.GetById(this.id).subscribe(
      (response: any) => {
        this.PartnerData = response;
      },
      (error: any) => {
        console.error('Error loading Partner data:', error);
      }
    );
  }

  updatePartenaire(id: number,PartnerData:Partenaire): void {
    this.PartServ.updatePartenaire(id,PartnerData).subscribe(
      (response: any) => {
        console.log('Partner updated successfully:', response);
      },
      (error: any) => {
        if(error.status==202){
          this.router.navigate(['/HomeAdmin/Partenaire']);
        console.error('Error updating Partner:', error);
      }}
    );
  }

}
