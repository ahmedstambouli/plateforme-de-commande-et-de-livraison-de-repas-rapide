import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { RouterModule, Routes } from '@angular/router';
import { SideBarAdminComponent } from './side-bar-admin/side-bar-admin.component';
import { DeliveryPersonComponent } from './delivery-person/delivery-person.component';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { LivreurPipePipe } from '../Pipes/livreur-pipe.pipe';
import { NewDeliveryPersonComponent } from './new-delivery-person/new-delivery-person.component';

const routes:Routes=[

  {path:"HomeAdmin" , component:HomeAdminComponent,children:
  [
    {path:"DeliveryPerson" , component:DeliveryPersonComponent}
  ]
},
]


@NgModule({
  declarations: [
    HomeAdminComponent,
    SideBarAdminComponent,
    DeliveryPersonComponent,
    LivreurPipePipe,
    NewDeliveryPersonComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminModelModule { }
