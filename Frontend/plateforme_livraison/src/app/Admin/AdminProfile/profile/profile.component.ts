import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from 'src/app/Model/Admin';
import { AdminService } from 'src/app/Service/admin-service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  admin:any;
  AdminData:Admin=new Admin();
  tabadmin:any=[];


  constructor(private AdmServ:AdminService,private route: ActivatedRoute,    private router: Router
    ){}

  ngOnInit():void{
    this.getAll()

    
  } 
  
  
  
  getAll(){
    return this.AdmServ.getAllInfo().subscribe({
      next: (response:any) => {
        this.admin = response;
        for (let index = 0; index < response.length; index++) {
           this.tabadmin = this.admin[index];

        }
        console.log(this.tabadmin);
        
      },
      error: (e:any) => console.log(e),
    });
           

    
  }

  updateAdmin(): void {
    console.log(this.tabadmin);
    
    this.AdmServ.updateAdmin(this.tabadmin).subscribe(
      (response: any) => {
        console.log('Admin updated successfully:', response);
      },
      (error: any) => {
        if (error.status === 202) {
          this.router.navigate(['/HomeAdmin/AdminProfile']);
        }
        console.error('Error updating Admin:', error);
      }
    );
  }


}
