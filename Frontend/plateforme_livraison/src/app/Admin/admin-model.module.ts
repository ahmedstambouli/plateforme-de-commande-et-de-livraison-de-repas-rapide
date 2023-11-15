import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeAdminComponent } from './DeliveryGest/home-admin/home-admin.component';
import { RouterModule, Routes } from '@angular/router';
import { SideBarAdminComponent } from './side-bar-admin/side-bar-admin.component';
import { DeliveryPersonComponent } from './DeliveryGest/delivery-person/delivery-person.component';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { LivreurPipePipe } from '../Pipes/livreur-pipe.pipe';
import { NewDeliveryPersonComponent } from './DeliveryGest/new-delivery-person/new-delivery-person.component';
import { PartenaireComponent } from './Partner/partenaire/partenaire.component';
import { UpdatePartenaireComponent } from './Partner/update-partenaire/update-partenaire.component';
import { ProfileComponent } from './AdminProfile/profile/profile.component';

const routes:Routes=[

  {path:"HomeAdmin" , component:HomeAdminComponent,children:
  [
    {path:"DeliveryPerson" , component:DeliveryPersonComponent},
    {path:"Partenaire",component:PartenaireComponent},
    { path:"update_partenaire/:id", component: UpdatePartenaireComponent },
    {path:"AdminProfile",component:ProfileComponent}
  ]
},
]


@NgModule({
  declarations: [
    HomeAdminComponent,
    SideBarAdminComponent,
    DeliveryPersonComponent,
    LivreurPipePipe,
    NewDeliveryPersonComponent,
    PartenaireComponent,
    UpdatePartenaireComponent,
    ProfileComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminModelModule { }
