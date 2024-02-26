import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Observable, from } from 'rxjs';
import { JobService } from '../../../services/job/job.service';
import { JobCategoryService } from '../../../services/job-category/job-category.service';
import { NzMessageService } from 'ng-zorro-antd/message';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  @ViewChild('listJob') listJob: ElementRef | undefined;

  branch: any;
  listCategory: any;

  constructor(
    private jobCategoryService: JobCategoryService,
    private msg: NzMessageService
  ) {}

  ngOnInit() {
    this.jobCategoryService.viewJobCategory().subscribe( (res) => {
      this.listCategory = res;
    },
    (err) => {
      this.msg.error('Error occurred !!!');
    })
    this.jobCategoryService.getAllBranch().subscribe(
      (res) => {
        this.branch = res;
      },
      (err) => {
        this.msg.error('Error occurred !!!');
      }
    );
  }
}
