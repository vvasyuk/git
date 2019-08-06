import { Routes, RouterModule } from '@angular/router';     

import { DashboardComponent } from './components/dashboard/dashboard.component';     
import { ManageComponent } from './components/manage/manage.component';
import { ImageComponent } from './components/image/image.component';          

const routes: Routes = [     
  {     
    path: '',     
    component: DashboardComponent     
  },     
  {     
    path: 'manage',     
    component: ManageComponent     
  },     
  {     
    path: 'image',     
    component: ImageComponent     
  }        
];     

export const AppRoutes = RouterModule.forRoot(routes);     