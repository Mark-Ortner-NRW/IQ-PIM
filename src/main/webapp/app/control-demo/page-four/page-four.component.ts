import { Component, OnInit } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { Brand, IBrand } from 'app/shared/model/brand.model';
import { BrandService } from 'app/entities/brand/brand.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { TreeTableModule } from 'primeng/treetable';
import { TreeNode } from 'primeng/api';

@Component({
  selector: 'jhi-page-one',
  templateUrl: './page-four.component.html',
  providers: [MessageService],
  styleUrls: ['page-four.component.scss'],
})
export class PageFourComponent implements OnInit {
  color: any = '#24244c';
  brands?: IBrand[] | any;
  brands2?: IBrand[] | any;
  cols: any[] = [
    { field: 'id', header: 'ID' },
    { field: 'name', header: 'Name' },
    { field: 'description', header: 'Description' },
    { field: 'createdAt', header: 'Created At' },
  ];
  clonedBrands: { [n: number]: Brand } = {};

  constructor(protected brandService: BrandService, private messageService: MessageService) {}
  ngOnInit(): void {
    this.brandService.query({}).subscribe(
      (res: HttpResponse<IBrand[]>) => this.onSuccess(res.body),
      () => this.onError()
    );
  }

  protected onSuccess(data: IBrand[] | null): void {
    this.brands = data || [];
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Got Data ... .' });
  }

  protected onError(): void {}
  /*
  onRowEditInit(brand: ) {
    //this.clonedBrands[brand.id] = {...brand};
  }

  onRowEditSave(brand: Brand) {
    //delete this.brandService[brand.id];
    this.messageService.add({severity:'success', summary: 'Success', detail:'Product is updated'});
  }

  onRowEditCancel(brand: Brand, index: number) {
    //this.brands2[index] = this.clonedBrands[brand.id];
    //delete this.brands2[brand.id];
  }
  */
}
