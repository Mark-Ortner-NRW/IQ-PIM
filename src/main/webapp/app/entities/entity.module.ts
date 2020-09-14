import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'brand',
        loadChildren: () => import('./brand/brand.module').then(m => m.InnoqPimBrandModule),
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.InnoqPimProductModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class InnoqPimEntityModule {}
